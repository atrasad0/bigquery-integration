package br.com.bigquery.system.commons.bigquery;

import br.com.bigquery.system.commons.bigquery.support.BigQueryColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BigQueryUtilsTest {

    @Getter
    @Setter
    @AllArgsConstructor
    @FieldNameConstants
    private static class PeopleTestWithBigQueryColumn {
        public static final String FIRST_NAME_COLUMN = "first_name";
        public static final String LAST_NAME_COLUMN = "last_name";

        private Long id;

        @BigQueryColumn(name = FIRST_NAME_COLUMN)
        private String firstName;

        @BigQueryColumn(name = LAST_NAME_COLUMN)
        private String lastName;

        private String gender;
    }

    @Test
    @DisplayName("Should return the column name specified in the BigQueryColumn annotation")
    void getFieldColumnName_withAnnotation() {
        Assertions.assertEquals(
                PeopleTestWithBigQueryColumn.FIRST_NAME_COLUMN,
                BigQueryUtils.getFieldColumnName(PeopleTestWithBigQueryColumn.class, PeopleTestWithBigQueryColumn.Fields.firstName)
        );

        Assertions.assertEquals(
                PeopleTestWithBigQueryColumn.LAST_NAME_COLUMN,
                BigQueryUtils.getFieldColumnName(PeopleTestWithBigQueryColumn.class, PeopleTestWithBigQueryColumn.Fields.lastName)
        );
    }

    @Test
    @DisplayName("Should return the field name when the BigQueryColumn annotation is not present")
    void getFieldColumnName_withoutAnnotation() {
        Assertions.assertEquals(
                PeopleTestWithBigQueryColumn.Fields.id,
                BigQueryUtils.getFieldColumnName(PeopleTestWithBigQueryColumn.class, PeopleTestWithBigQueryColumn.Fields.id)
        );

        Assertions.assertEquals(
                PeopleTestWithBigQueryColumn.Fields.gender,
                BigQueryUtils.getFieldColumnName(PeopleTestWithBigQueryColumn.class, PeopleTestWithBigQueryColumn.Fields.gender)
        );
    }

    @Test
    @DisplayName("Should throw an error when the field is not found in the class")
    void getFieldColumnName_fieldNotFound() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> BigQueryUtils.getFieldColumnName(PeopleTestWithBigQueryColumn.class, "anyField"));
    }
}