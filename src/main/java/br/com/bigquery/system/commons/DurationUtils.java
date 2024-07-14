package br.com.bigquery.system.commons;

import lombok.val;

import java.time.Duration;
import java.util.Objects;

/**
 * Classe com métodos utilitários para o uso de {@link Duration}
 *
 * @author Yuri Gonçalves
 */
public class DurationUtils {
    private DurationUtils() {
        throw new IllegalStateException("utility class");
    }

    /**
     * Formata uma duração em formato legível ao usuário, exibindo todas as unidades de tempo
     * disponíveis.
     */
    public static String format(Duration duration) {
        if (Objects.isNull(duration)) {
            return "";
        }

        val builder = new StringBuilder();

        if (duration.toDays() != 0)
            builder.append(duration.toDays()).append("d ");

        if (duration.toHoursPart() != 0)
            builder.append(duration.toHoursPart()).append("h ");

        if (duration.toMinutesPart() != 0)
            builder.append(duration.toMinutesPart()).append("min ");

        if (builder.isEmpty() || duration.toSecondsPart() != 0)
            builder.append(duration.toSecondsPart()).append("s ");

        return builder.toString().strip();
    }

}
