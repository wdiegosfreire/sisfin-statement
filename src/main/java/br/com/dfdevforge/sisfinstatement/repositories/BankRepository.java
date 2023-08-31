package br.com.dfdevforge.sisfinstatement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.dfdevforge.sisfinstatement.entities.BankEntity;

public interface BankRepository extends JpaRepository<BankEntity, Long>, JpaSpecificationExecutor<BankEntity> {
	public BankEntity findByIdentity(Long identity);
	public List<BankEntity> findByUserIdentityOrderByNameAsc(Long userIdentity);
}