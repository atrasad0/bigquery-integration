package br.com.bigquery.system.configuration;

import br.com.bigquery.system.commons.exceptions.InitializationException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryException;
import com.google.cloud.bigquery.BigQueryOptions;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Configuration
public class BigQueryServiceConfig {
    @Value("${bigquery.credentials.path}")
    private String credentialsPath;

    @Value("${bigquery.project.id}")
    private String projectId;
    @Bean
    public BigQuery bigQueryService() {
        try (FileInputStream serviceAccount = new FileInputStream(credentialsPath)) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

            log.info("BigQuery client initialized successfully!");

            return BigQueryOptions.newBuilder()
               .setCredentials(credentials)
               .setProjectId(projectId)
               .build()
               .getService();

        } catch (IOException e) {
            log.error("Error on BigQuery client initialization: {}", e.getMessage(), e);
            throw new InitializationException(e.getMessage(), e);
       }
    }
}
