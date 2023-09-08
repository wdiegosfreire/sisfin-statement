package br.com.dfdevforge.sisfinstatement.services.statementtype;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.CommonService;
import br.com.dfdevforge.sisfinstatement.exceptions.RequiredFieldNotFoundException;
import br.com.dfdevforge.sisfinstatement.repositories.StatementTypeRepository;

@Service
public class StatementTypeExecuteRegistrationService extends StatementTypeService implements CommonService {
	@Autowired private StatementTypeRepository statementTypeRepository;

	@Override
	public void executeBusinessRule() throws BaseException {
		this.checkRequiredFields();
		this.saveStatementType();
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		this.setArtifact("statementTypeRegistered", this.statementTypeParam);
		return super.returnBusinessData();
	}

	private void checkRequiredFields() throws RequiredFieldNotFoundException {
		List<String> errorList = new ArrayList<>();

		if (this.statementTypeParam.getName() == null || this.statementTypeParam.getName().equals(""))
			errorList.add("Please, enter name.");
		if (this.statementTypeParam.getUserIdentity() == null)
			errorList.add("Please, the bank need to be associated with a user.");

		if (errorList != null && !errorList.isEmpty())
			throw new RequiredFieldNotFoundException("Required Field Not Found", errorList);
	}

	private void saveStatementType() throws BaseException {
		this.statementTypeRepository.save(this.statementTypeParam);
	}
}