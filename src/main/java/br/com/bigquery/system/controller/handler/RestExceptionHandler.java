package br.com.bigquery.system.controller.handler;

import br.com.bigquery.system.controller.handler.support.StandardExceptionDetails;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Classe que lida com a padronização de mensagens de erro nos controllers da aplicação.
 */
@Slf4j
@ControllerAdvice
public class RestExceptionHandler {
    private final String ERROR_CONTACT = "Entre em contato com os desenvolvedores.";
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardExceptionDetails> handleException(Exception e) {
        log.error(e.getMessage(), e);

        val response = StandardExceptionDetails.builder()
                .when(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .title("Erro no servi\u00E7o da aplica\u00E7\u00E3o")
                .detail(ERROR_CONTACT)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardExceptionDetails> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);

        val response = StandardExceptionDetails.builder()
                .when(LocalDateTime.now())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .title("Erro processando a requisi\u00E7\u00E3o")
                .detail(ERROR_CONTACT)
                .build();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardExceptionDetails> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);

        val response = StandardExceptionDetails.builder()
                .when(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Erro nos par\u00E2metros da requisi\u00E7\u00E3o")
                .detail(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}