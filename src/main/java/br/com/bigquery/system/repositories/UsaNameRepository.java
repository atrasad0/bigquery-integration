package br.com.bigquery.system.repositories;

import br.com.bigquery.system.models.UsaName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsaNameRepository extends JpaRepository<UsaName, Long> {
}
