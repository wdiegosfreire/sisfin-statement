package br.com.dfdevforge.sisfinstatement.services.statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.exceptions.RequiredFieldNotFoundException;
import br.com.dfdevforge.sisfinstatement.repositories.StatementRepository;

@Service
public class StatementExecuteImportService extends StatementBaseService implements CommonService {
	@Autowired private StatementRepository statementRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.checkRequiredFields();
		this.saveStatement();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("statementRegistered", this.statementParam);
		return super.returnBusinessData();
	}

	private void checkRequiredFields() throws RequiredFieldNotFoundException {
		List<String> errorList = new ArrayList<>();

		if (this.statementParam.getYear() == null)
			errorList.add("Please, enter the year.");
		if (this.statementParam.getUserIdentity() == null)
			errorList.add("Please, the statement need to be associated with a user.");

		if (errorList != null && !errorList.isEmpty())
			throw new RequiredFieldNotFoundException("Required Field Not Found", errorList);
	}

	private void saveStatement() throws BaseException {
		this.statementRepository.save(this.statementParam);
	}
}