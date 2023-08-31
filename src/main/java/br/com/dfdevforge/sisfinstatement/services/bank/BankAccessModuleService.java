package br.com.dfdevforge.sisfinstatement.services.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.repositories.BankRepository;

@Service
public class BankAccessModuleService extends BankBaseService implements CommonService {
	@Autowired private BankRepository bankRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findAllBanks();
	}

	private void findAllBanks() {
		this.setArtifact("bankList", this.bankRepository.findByUserIdentityOrderByNameAsc(bankParam.getUserIdentity()));
	}
}