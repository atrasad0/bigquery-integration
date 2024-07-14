package br.com.bigquery.system.commons.bigquery.support;

import com.google.cloud.bigquery.FieldValueList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Interface para converter resultados do BigQuery em entidades do sistema.
 *
 * @param <T> o tipo da entidade a ser retornado
 * @author Yuri Gonçalves
 */
public interface AwareConvertBigQueryResults<T> {

    /**
     * Converte um {@link FieldValueList} em uma entidade
     *
     * @param row O {@link FieldValueList} a ser convertido
     * @return Uma instância da entidade representando os dados no {@link FieldValueList} fornecido
     */
    T fromFieldValueList(FieldValueList row);

    /**
     * Converte um {@link Iterable} de {@link FieldValueList} em uma lista de entidades.
     *
     * @param results um {@link Iterable} de {@link FieldValueList} a ser convertido
     * @return uma lista de entidades representando os dados nos {@link FieldValueList} fornecidos
     */
    default List<T> fromBigQueryResult(Iterable<FieldValueList> results) {
        if (Objects.isNull(results) || !results.iterator().hasNext()) {
            return Collections.emptyList();
        }
        Iterator<FieldValueList> fieldValueListIterator = results.iterator();
        List<T> entities = new ArrayList<>();

        while (fieldValueListIterator.hasNext()) {
            FieldValueList row = fieldValueListIterator.next();
            T entity = this.fromFieldValueList(row);
            entities.add(entity);
        }
        return entities;
    }
}
