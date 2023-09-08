package br.com.dfdevforge.sisfinstatement.services.bank;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.entities.BankEntity;
import br.com.dfdevforge.sisfinstatement.exceptions.DataForExclusionNotFoundException;
import br.com.dfdevforge.sisfinstatement.repositories.BankRepository;

@Service
public class BankExecuteExclusionService extends BankBaseService implements CommonService {
	@Autowired private BankRepository bankRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.findById();
		this.deleteBank();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("bankRegistered", this.bankParam);
		return super.returnBusinessData();
	}

	private void findById() throws DataForExclusionNotFoundException {
		BankEntity bank = this.bankRepository.findByIdentity(this.bankParam.getIdentity());

		if (bank == null)
			throw new DataForExclusionNotFoundException();
	}

	private void deleteBank() throws BaseException {
		this.bankRepository.delete(this.bankParam);
	}
}