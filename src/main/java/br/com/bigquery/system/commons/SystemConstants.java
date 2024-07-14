package br.com.bigquery.system.commons;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

public class SystemConstants {

    private SystemConstants() {
        throw new IllegalStateException("utility class");
    }

    /* DATABASE */
    public static final String DB_URL_ENV = "DB_URL";
    public static final String DB_USERNAME_ENV = "DB_USERNAME";
    public static final String DB_PASSWORD_ENV = "DB_PASSWORD";


    /** O fuso-horario padrao a ser utilizado no sistema quando um valor explicito nao esta disponivel. */
    public static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone("America/Sao_Paulo");

    /** O {@link Locale} a ser utilizado no sistema quando um valor explicito nao esta disponivel. */
    public static final Locale DEFAULT_LOCALE = Locale.forLanguageTag("pt-BR");

    /** O ZONE_ID a ser utilizado no sistema quando um valor explicito nao esta disponivel. */
    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("America/Sao_Paulo");
}
