package br.com.dfdevforge.sisfinstatement.services.statement;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.entities.StatementEntity;
import br.com.dfdevforge.sisfinstatement.exceptions.DataForExclusionNotFoundException;
import br.com.dfdevforge.sisfinstatement.repositories.StatementRepository;

@Service
public class StatementExecuteExclusionService extends StatementBaseService implements CommonService {
	@Autowired private StatementRepository statementRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findById();
		this.deleteStatement();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("statementRegistered", this.statementParam);
		return super.returnBusinessData();
	}

	private void findById() throws DataForExclusionNotFoundException {
		StatementEntity statement = this.statementRepository.findByIdentity(this.statementParam.getIdentity());

		if (statement == null)
			throw new DataForExclusionNotFoundException();
	}

	private void deleteStatement() throws BaseException {
		this.statementRepository.delete(this.statementParam);
	}
}