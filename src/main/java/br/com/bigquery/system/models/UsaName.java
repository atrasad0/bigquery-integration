package br.com.bigquery.system.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Builder

@Entity
public class UsaName implements Serializable {

    @Serial
    private static final long serialVersionUID = -8337274213142606896L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String state;
    private String gender;
    private String name;

    @Column(name = "BIRTH_YEAR")
    private Integer year;

    @Column(name = "OCCURRENCES_NUMBER")
    private Long number;
}
