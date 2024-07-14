package br.com.bigquery.system.configuration;

import br.com.bigquery.system.commons.SystemConstants;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
            .url(System.getenv(SystemConstants.DB_URL_ENV))
            .username(System.getenv(SystemConstants.DB_USERNAME_ENV))
            .password(System.getenv(SystemConstants.DB_PASSWORD_ENV))
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .build();
    }
}
