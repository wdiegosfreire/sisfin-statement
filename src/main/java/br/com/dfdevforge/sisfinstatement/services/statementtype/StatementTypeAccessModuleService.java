package br.com.dfdevforge.sisfinstatement.services.statementtype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.repositories.StatementTypeRepository;

@Service
public class StatementTypeAccessModuleService extends StatementTypeService implements CommonService {
	@Autowired private StatementTypeRepository statementTypeRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findAllStatementTypes();
	}

	private void findAllStatementTypes() {
		this.setArtifact("statementTypeList", this.statementTypeRepository.findByUserIdentityOrderByNameAsc(statementTypeParam.getUserIdentity()));
	}
}