package br.com.bigquery.system.service.impl;

import br.com.bigquery.system.commons.SystemConstants;
import br.com.bigquery.system.commons.bigquery.support.AwareConvertBigQueryResults;
import br.com.bigquery.system.models.UsaName;
import br.com.bigquery.system.models.to.DataIntegrationLogTO;
import br.com.bigquery.system.repositories.UsaNameRepository;
import br.com.bigquery.system.service.BigQueryService;
import br.com.bigquery.system.service.UsaNameService;
import com.google.cloud.bigquery.QueryJobConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsaNameServiceImpl implements UsaNameService {

    private final BigQueryService bigQueryService;
    private final UsaNameRepository usaNameRepository;
    private final AwareConvertBigQueryResults<UsaName> usaNameMapper;

    @Override
    public DataIntegrationLogTO processUsaNamesDataInegration() {
        val startTime = LocalDateTime.now(SystemConstants.DEFAULT_ZONE_ID);
        val query = "SELECT * FROM `bigquery-public-data.usa_names.usa_1910_current` LIMIT 100";

        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query)
            .setUseLegacySql(false)
            .build();

        log.info("UsaName data integration started");
        val updatedRecords = bigQueryService.processDataIntegration(usaNameRepository, usaNameMapper, queryConfig);

        return DataIntegrationLogTO.builder()
                .database("USA_NAMES")
                .startTime(startTime)
                .endTime(LocalDateTime.now(SystemConstants.DEFAULT_ZONE_ID))
                .updatedRecords(updatedRecords)
                .build();
    }
}