package br.com.dfdevforge.sisfinstatement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.dfdevforge.sisfinstatement.entities.StatementTypeEntity;

public interface StatementTypeRepository extends JpaRepository<StatementTypeEntity, Long>, JpaSpecificationExecutor<StatementTypeEntity> {
	public StatementTypeEntity findByIdentity(Long identity);
	public List<StatementTypeEntity> findByUserIdentityOrderByNameAsc(Long userIdentity);
}