package br.com.bigquery.system.service;

import br.com.bigquery.system.models.to.DataIntegrationLogTO;

public interface UsaNameService {
    /**
     * Extrai dados de nomes dos EUA de uma base de dados pública do BigQuery e os salva na base de dados da aplicação.
     */
    DataIntegrationLogTO processUsaNamesDataInegration();
}
