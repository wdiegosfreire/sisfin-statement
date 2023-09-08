package br.com.dfdevforge.sisfinstatement.services.statementtype;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.entities.StatementTypeEntity;
import br.com.dfdevforge.sisfinstatement.exceptions.DataForExclusionNotFoundException;
import br.com.dfdevforge.sisfinstatement.repositories.StatementTypeRepository;

@Service
public class StatementTypeExecuteExclusionService extends StatementTypeService implements CommonService {
	@Autowired private StatementTypeRepository statementTypeRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findByIdentity();
		this.deleteStatementType();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("statementTypeRegistered", this.statementTypeParam);
		return super.returnBusinessData();
	}

	private void findByIdentity() throws DataForExclusionNotFoundException {
		StatementTypeEntity statementType = this.statementTypeRepository.findByIdentity(this.statementTypeParam.getIdentity());

		if (statementType == null)
			throw new DataForExclusionNotFoundException();
	}

	private void deleteStatementType() throws BaseException {
		this.statementTypeRepository.delete(this.statementTypeParam);
	}
}