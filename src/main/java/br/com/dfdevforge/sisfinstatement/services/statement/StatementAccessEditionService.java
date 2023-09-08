package br.com.dfdevforge.sisfinstatement.services.statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.entities.StatementEntity;
import br.com.dfdevforge.sisfinstatement.exceptions.DataForEditionNotFoundException;
import br.com.dfdevforge.sisfinstatement.repositories.StatementRepository;

@Service
public class StatementAccessEditionService extends StatementBaseService implements CommonService {
	@Autowired private StatementRepository statementRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findByIdentity();
	}

	private void findByIdentity() throws DataForEditionNotFoundException {
		StatementEntity statement = this.statementRepository.findByIdentity(this.statementParam.getIdentity());

		if (statement == null)
			throw new DataForEditionNotFoundException();

		this.setArtifact("bank", statement);
	}
}