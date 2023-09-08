package br.com.dfdevforge.sisfinstatement.services.statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.repositories.StatementRepository;

@Service
public class StatementAccessModuleService extends StatementBaseService implements CommonService {
	@Autowired private StatementRepository StatementRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findAllStatements();
	}

	private void findAllStatements() {
		this.setArtifact("statementList", this.StatementRepository.findByUserIdentityOrderByYearAscMonthAsc(statementParam.getUserIdentity()));
	}
}