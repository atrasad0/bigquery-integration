package br.com.bigquery.system.service.impl;

import br.com.bigquery.system.commons.bigquery.support.AwareConvertBigQueryResults;
import br.com.bigquery.system.service.BigQueryService;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class BigQueryServiceImpl implements BigQueryService {

    private final BigQuery bigQueryClient;

    @Override
    @Transactional
    public <T, ID> long processDataIntegration(JpaRepository<T, ID> repository, AwareConvertBigQueryResults<T> mapper,
                                               QueryJobConfiguration queryConfig, long... pageSize) {

        long requestPageSize = 500;
        if (Objects.nonNull(pageSize) && pageSize.length > 0) {
            requestPageSize = pageSize[0];
        }

        Job job = bigQueryClient.create(JobInfo.of(queryConfig));
        BigQuery.QueryResultsOption queryResultsOption = BigQuery.QueryResultsOption.pageSize(requestPageSize);

        try {
            TableResult result = job.getQueryResults(queryResultsOption);
            long totalRows = result.getTotalRows();

            if (totalRows == 0) {
                log.info("No items found in the search, integration process skipped");
                return 0;
            }

            log.info("Found {} items. Using page size of {}. Now saving in database...", totalRows, requestPageSize);

            repository.saveAll(mapper.fromBigQueryResult(result.getValues()));

            while (result.hasNextPage()) {
                result = result.getNextPage();
                repository.saveAll(mapper.fromBigQueryResult(result.getValues()));
            }

            log.info("Data integration finished");
            return totalRows;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Query interrupted", e);
        }
    }
}