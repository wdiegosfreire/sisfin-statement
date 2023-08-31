package br.com.dfdevforge.sisfinstatement.services.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.entities.BankEntity;
import br.com.dfdevforge.sisfinstatement.exceptions.DataForEditionNotFoundException;
import br.com.dfdevforge.sisfinstatement.repositories.BankRepository;

@Service
public class BankAccessEditionService extends BankBaseService implements CommonService {
	@Autowired private BankRepository bankRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findById();
	}

	private void findById() throws DataForEditionNotFoundException {
		BankEntity bank = this.bankRepository.findByIdentity(this.bankParam.getIdentity());

		if (bank == null)
			throw new DataForEditionNotFoundException();

		this.setArtifact("bank", bank);
	}
}