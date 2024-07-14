package br.com.bigquery.system.commons.bigquery;

import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.FieldValueList;
import lombok.NonNull;

/**
 * Classe utilitária para interação com o Google BigQuery.
 * Esta classe fornece diversos métodos utilitários para manipulação de operações do BigQuery.
 *
 * @author Yuri Gonçalves
 */
public class BigQueryUtils {
    public static String getStringValue(@NonNull FieldValueList row, @NonNull String field) {
        FieldValue value = row.get(field);
        return value.isNull() ? null : value.getStringValue();
    }

    public static Double getDoubleValue(@NonNull FieldValueList row, @NonNull String field) {
        FieldValue value = row.get(field);
        return value.isNull() ? null : value.getDoubleValue();
    }

    public static Integer getIntValue(@NonNull FieldValueList row, @NonNull String field) {
        FieldValue value = row.get(field);
        return value.isNull() ? null : value.getNumericValue().intValue();
    }

    public static Long getLongValue(@NonNull FieldValueList row, @NonNull String field) {
        FieldValue value = row.get(field);
        return value.isNull() ? null : value.getLongValue();
    }

    public static <T> T getValue(@NonNull FieldValueList row, @NonNull String field, Class<T> targetType) {
        FieldValue value = row.get(field);
        return value.isNull() ? null : targetType.cast(value.getValue());
    }
}
