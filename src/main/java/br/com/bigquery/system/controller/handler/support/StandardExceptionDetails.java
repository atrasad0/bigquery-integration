package br.com.bigquery.system.controller.handler.support;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Classe que representa uma mensagem padrão de Exceptions da aplicação.
 */
@Builder
@Getter
public class StandardExceptionDetails {
    private final String title;
    private final String detail;
    private final int status;
    private final LocalDateTime when;

}