package br.com.bigquery.system.service;

import br.com.bigquery.system.commons.bigquery.support.AwareConvertBigQueryResults;
import com.google.cloud.bigquery.QueryJobConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BigQueryService {

    /**
     * Serviço para extrair dados de uma base de dados BigQuery e salvá-los como uma entidade do tipo {@code T} na base de dados do sistema utilizando um repositório JPA.
     *
     * @param repository O repositório JPA onde as entidades serão salvas.
     * @param mapper O mapeador que converte os resultados do BigQuery em instâncias de entidade.
     * @param queryConfig A configuração da consulta do BigQuery.
     * @param pageSize O tamanho opcional da página para paginação dos resultados da consulta. Se não informado um valor default será adicionado.
     *
     * @return A quantidade de linhas atualizadas
     */
    <T,ID> long processDataIntegration(JpaRepository<T, ID> repository, AwareConvertBigQueryResults<T> mapper,
                                       QueryJobConfiguration queryConfig, long... pageSize);
}
