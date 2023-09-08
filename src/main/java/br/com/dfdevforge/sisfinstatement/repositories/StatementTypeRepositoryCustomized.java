package br.com.dfdevforge.sisfinstatement.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.com.dfdevforge.common.util.Utils;
import br.com.dfdevforge.sisfinstatement.entities.StatementTypeEntity;

@Repository
public class StatementTypeRepositoryCustomized {
	private final EntityManager entityManager;

	public StatementTypeRepositoryCustomized(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<StatementTypeEntity> searchInAllProperties(StatementTypeEntity bank) {
		StringBuilder whereClause = new StringBuilder();

		whereClause.append(" stt.userIdentity = :userIdentity ");

		whereClause.append(" and ( ");
		whereClause.append("   stt.name like :filter ");
		whereClause.append(" ) ");

		if (Utils.value.isNumber(bank.getFilter()))
			whereClause.append(" or stt.identity like :filter ");

		StringBuilder jpql = new StringBuilder();

		jpql.append("select stt from StatementTypeEntity as stt where " + whereClause);

		var query = this.entityManager.createQuery(jpql.toString(), StatementTypeEntity.class);

		query.setParameter("userIdentity", bank.getUserIdentity());
		query.setParameter("filter", "%" + bank.getFilter() + "%");
		
		return query.getResultList();
	}
}