package br.com.bigquery.system.models.to;

import br.com.bigquery.system.commons.DurationUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Getter
@Setter
public class DataIntegrationLogTO {
    private String database;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private long updatedRecords;

    public String getIntegrationTime() {
        if (Objects.isNull(this.startTime) || Objects.isNull(this.endTime)) {
            return "";
        }

        return DurationUtils.format(Duration.between(this.startTime, this.endTime));
    }
}
