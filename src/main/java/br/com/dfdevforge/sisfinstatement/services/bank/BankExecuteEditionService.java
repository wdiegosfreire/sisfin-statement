package br.com.dfdevforge.sisfinstatement.services.bank;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.entities.BankEntity;
import br.com.dfdevforge.sisfinstatement.exceptions.DataForEditionNotFoundException;
import br.com.dfdevforge.sisfinstatement.repositories.BankRepository;

@Service
public class BankExecuteEditionService extends BankBaseService implements CommonService {
	@Autowired private BankRepository bankRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findById();
		this.editBank();
		this.findAllBanks();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("bankRegistered", this.bankParam);
		return super.returnBusinessData();
	}

	private void findById() throws DataForEditionNotFoundException {
		BankEntity bank = this.bankRepository.findByIdentity(this.bankParam.getIdentity());

		if (bank == null)
			throw new DataForEditionNotFoundException();
	}

	private void editBank() throws BaseException {
		this.bankRepository.save(this.bankParam);
	}

	private void findAllBanks() {
		this.setArtifact("bankList", this.bankRepository.findByUserIdentityOrderByNameAsc(bankParam.getUserIdentity()));
	}
}