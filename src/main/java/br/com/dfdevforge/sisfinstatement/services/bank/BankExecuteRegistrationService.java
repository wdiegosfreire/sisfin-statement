package br.com.dfdevforge.sisfinstatement.services.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.exceptions.RequiredFieldNotFoundException;
import br.com.dfdevforge.sisfinstatement.repositories.BankRepository;

@Service
public class BankExecuteRegistrationService extends BankBaseService implements CommonService {
	@Autowired private BankRepository bankRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.checkRequiredFields();
		this.saveBank();
		this.findAllBanks();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("bankRegistered", this.bankParam);
		return super.returnBusinessData();
	}

	private void checkRequiredFields() throws RequiredFieldNotFoundException {
		List<String> errorList = new ArrayList<>();

		if (this.bankParam.getName() == null || this.bankParam.getName().equals(""))
			errorList.add("Please, enter name.");
		if (this.bankParam.getUserIdentity() == null)
			errorList.add("Please, the bank need to be associated with a user.");

		if (errorList != null && !errorList.isEmpty())
			throw new RequiredFieldNotFoundException("Required Field Not Found", errorList);
	}

	private void saveBank() throws BaseException {
		this.bankRepository.save(this.bankParam);
	}

	private void findAllBanks() {
		this.setArtifact("bankList", this.bankRepository.findByUserIdentityOrderByNameAsc(bankParam.getUserIdentity()));
	}
}