package br.com.bigquery.system.commons.bigquery.mappers;

import br.com.bigquery.system.commons.bigquery.BigQueryUtils;
import br.com.bigquery.system.commons.bigquery.support.AwareConvertBigQueryResults;
import br.com.bigquery.system.models.UsaName;
import com.google.cloud.bigquery.FieldValueList;
import org.springframework.stereotype.Component;

@Component
public class UsaNameMapper implements AwareConvertBigQueryResults<UsaName> {
    @Override
    public UsaName fromFieldValueList(FieldValueList row) {
        return UsaName.builder()
            .name(BigQueryUtils.getStringValue(row, UsaName.Fields.name))
            .gender(BigQueryUtils.getStringValue(row, UsaName.Fields.gender))
            .state(BigQueryUtils.getStringValue(row, UsaName.Fields.state))
            .year(BigQueryUtils.getIntValue(row, UsaName.Fields.year))
            .number(BigQueryUtils.getLongValue(row, UsaName.Fields.number))
            .build();

        }
}
