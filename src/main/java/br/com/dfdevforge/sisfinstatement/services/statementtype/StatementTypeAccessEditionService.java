package br.com.dfdevforge.sisfinstatement.services.statementtype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.entities.BankEntity;
import br.com.dfdevforge.sisfinstatement.entities.StatementTypeEntity;
import br.com.dfdevforge.sisfinstatement.exceptions.DataForEditionNotFoundException;
import br.com.dfdevforge.sisfinstatement.repositories.BankRepository;
import br.com.dfdevforge.sisfinstatement.repositories.StatementTypeRepository;

@Service
public class StatementTypeAccessEditionService extends StatementTypeService implements CommonService {
	@Autowired private StatementTypeRepository statementTypeRepository;

	@Autowired private BankRepository bankRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findByIdentity();
		this.findBanks();
	}

	private void findByIdentity() throws DataForEditionNotFoundException {
		StatementTypeEntity statementType = this.statementTypeRepository.findByIdentity(this.statementTypeParam.getIdentity());

		if (statementType == null)
			throw new DataForEditionNotFoundException();

		this.setArtifact("statementType", statementType);
	}

	private void findBanks() throws DataForEditionNotFoundException {
		List<BankEntity> bankListCombo = this.bankRepository.findByUserIdentityOrderByNameAsc(this.statementTypeParam.getUserIdentity());
		
		if (bankListCombo == null)
			throw new DataForEditionNotFoundException();
		
		this.setArtifact("bankListCombo", bankListCombo);
	}
}