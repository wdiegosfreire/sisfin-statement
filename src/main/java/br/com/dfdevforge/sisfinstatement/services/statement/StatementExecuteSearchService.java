package br.com.dfdevforge.sisfinstatement.services.statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.repositories.StatementRepository;
import br.com.dfdevforge.sisfinstatement.repositories.StatementRepositoryCustomized;

@Service
public class StatementExecuteSearchService extends StatementBaseService implements CommonService {
	@Autowired private StatementRepository statementRepository;
	@Autowired private StatementRepositoryCustomized statementRepositoryCustomized;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findAllStatements();
	}

	private void findAllStatements() {
		if (this.statementParam.getFilter() == null || this.statementParam.getFilter().contentEquals(""))
			this.setArtifact("statementList", this.statementRepository.findByUserIdentityOrderByYearAscMonthAsc(statementParam.getUserIdentity()));
		else
			this.setArtifact("statementList", this.statementRepositoryCustomized.searchInAllProperties(this.statementParam));
	}
}