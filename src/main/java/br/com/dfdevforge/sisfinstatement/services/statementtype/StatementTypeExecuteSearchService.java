package br.com.dfdevforge.sisfinstatement.services.statementtype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.repositories.StatementTypeRepository;
import br.com.dfdevforge.sisfinstatement.repositories.StatementTypeRepositoryCustomized;

@Service
public class StatementTypeExecuteSearchService extends StatementTypeService implements CommonService {
	@Autowired private StatementTypeRepository statementTypeRepository;
	@Autowired private StatementTypeRepositoryCustomized statementTypeRepositoryCustomized;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findAllStatementTypes();
	}

	private void findAllStatementTypes() {
		if (this.statementTypeParam.getFilter() == null || this.statementTypeParam.getFilter().contentEquals(""))
			this.setArtifact("statementTypeList", this.statementTypeRepository.findByUserIdentityOrderByNameAsc(statementTypeParam.getUserIdentity()));
		else
			this.setArtifact("statementTypeList", this.statementTypeRepositoryCustomized.searchInAllProperties(this.statementTypeParam));
	}
}