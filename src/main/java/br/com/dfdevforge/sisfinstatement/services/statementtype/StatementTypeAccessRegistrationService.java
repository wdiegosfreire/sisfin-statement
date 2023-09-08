package br.com.dfdevforge.sisfinstatement.services.statementtype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.entities.BankEntity;
import br.com.dfdevforge.sisfinstatement.exceptions.DataForEditionNotFoundException;
import br.com.dfdevforge.sisfinstatement.repositories.BankRepository;

@Service
public class StatementTypeAccessRegistrationService extends StatementTypeService implements CommonService {
	@Autowired private BankRepository bankRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findBanks();
	}

	private void findBanks() throws DataForEditionNotFoundException {
		List<BankEntity> bankListCombo = this.bankRepository.findByUserIdentityOrderByNameAsc(this.statementTypeParam.getUserIdentity());
		
		if (bankListCombo == null)
			throw new DataForEditionNotFoundException();
		
		this.setArtifact("bankListCombo", bankListCombo);
	}
}