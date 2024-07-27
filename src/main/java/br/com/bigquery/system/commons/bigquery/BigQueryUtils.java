package br.com.bigquery.system.commons.bigquery;

import br.com.bigquery.system.commons.bigquery.support.BigQueryColumn;
import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.FieldValueList;
import lombok.NonNull;
import lombok.val;
import org.springframework.util.ReflectionUtils;

import java.util.Optional;

/**
 * Classe utilitária para interação com o Google BigQuery.
 * Esta classe fornece diversos métodos utilitários para manipulação de operações do BigQuery.
 *
 * @author Yuri Gonçalves
 */
public class BigQueryUtils {

    private BigQueryUtils() {
        throw new IllegalStateException("utility class");
    }

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

    /**
     * Obtém o nome da coluna BigQuery a partir da anotação {@link BigQueryColumn} do campo especificado.
     * Se a anotação não estiver presente, retorna o nome do campo.
     *
     * @param clazz Classe na qual o campo deve ser encontrado.
     * @param fieldName Nome do campo para buscar a anotação.
     * @param <T> Tipo da classe.
     *
     * @return Nome da coluna conforme especificado na anotação @BigQueryColumn, ou o nome do campo se a anotação não estiver presente.
     * @throws NullPointerException Se o campo com o nome especificado não for encontrado.
     */
    public static <T> String getFieldColumnName(@NonNull Class<T>clazz, @NonNull String fieldName) {
        val field = Optional.ofNullable(ReflectionUtils.findField(clazz, fieldName))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Field: [%s] not found in [%s] class", fieldName, clazz.getSimpleName())));

        if (field.isAnnotationPresent(BigQueryColumn.class)) {
            return field.getAnnotation(BigQueryColumn.class).name();
        }

        return field.getName();
    }
}
