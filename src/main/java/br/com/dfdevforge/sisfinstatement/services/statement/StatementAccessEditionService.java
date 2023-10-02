package br.com.dfdevforge.sisfinstatement.services.statement;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.entities.StatementEntity;
import br.com.dfdevforge.sisfinstatement.exceptions.DataForEditionNotFoundException;
import br.com.dfdevforge.sisfinstatement.repositories.StatementItemRepository;
import br.com.dfdevforge.sisfinstatement.repositories.StatementRepository;

@Service
public class StatementAccessEditionService extends StatementBaseService implements CommonService {
	@Autowired private StatementRepository statementRepository;
	@Autowired private StatementItemRepository statementItemRepository;

	private StatementEntity statementResult;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findByIdentity();
		this.findItemsOfStatement();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("statement", this.statementResult);
		return super.returnBusinessData();
	}

	private void findByIdentity() throws DataForEditionNotFoundException {
		this.statementResult = this.statementRepository.findByIdentity(this.statementParam.getIdentity());

		if (statementResult == null)
			throw new DataForEditionNotFoundException();
	}

	private void findItemsOfStatement() {
		this.statementResult.setStatementItemList(statementItemRepository.findByStatement(this.statementParam));			
	}
}