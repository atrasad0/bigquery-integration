package br.com.bigquery.system.commons.exceptions;

import java.io.Serial;

public class InitializationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -5703711151713429332L;
    public InitializationException(String message) {
        super(message);
    }

    public InitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
