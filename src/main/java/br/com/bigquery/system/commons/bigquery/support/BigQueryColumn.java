package br.com.bigquery.system.commons.bigquery.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *A anotação {@code BigQueryColumn} é usada para mapear campos de uma classe Java para colunas específicas em uma tabela do BigQuery.
 * <p>Use esta anotação em campos de uma classe para indicar o nome da coluna correspondente no BigQuery.</p>
 *
 * <pre>
 * {@code
 *      public class People {
 *          @BigQueryColumn(name = "first_name")
 *          private String firstName;
 *
 *      }
 * }
 * </pre>
 *
 * @author Yuri Gonçalves
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BigQueryColumn {
    String name();
}
