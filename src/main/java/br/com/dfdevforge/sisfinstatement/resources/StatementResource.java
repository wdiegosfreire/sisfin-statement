package br.com.dfdevforge.sisfinstatement.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dfdevforge.common.entities.ResourceDataEntity;
import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.sisfinstatement.entities.StatementEntity;
import br.com.dfdevforge.sisfinstatement.services.statement.StatementAccessEditionService;
import br.com.dfdevforge.sisfinstatement.services.statement.StatementAccessModuleService;
import br.com.dfdevforge.sisfinstatement.services.statement.StatementExecuteEditionService;
import br.com.dfdevforge.sisfinstatement.services.statement.StatementExecuteExclusionService;
import br.com.dfdevforge.sisfinstatement.services.statement.StatementExecuteImportService;
import br.com.dfdevforge.sisfinstatement.services.statement.StatementExecuteSearchService;

@RestController
@RequestMapping(value = "/statement")
public class StatementResource {
	private ResourceDataEntity resourceData = new ResourceDataEntity();

	@Autowired private StatementAccessModuleService statementAccessModuleService;
	@Autowired private StatementAccessEditionService statementAccessEditionService;
	@Autowired private StatementExecuteSearchService statementExecuteSearchService;
	@Autowired private StatementExecuteEditionService statementExecuteEditionService;
	@Autowired private StatementExecuteExclusionService statementExecuteExclusionService;
	@Autowired private StatementExecuteImportService statementExecuteImportService;
	

	@PostMapping(value = "/accessModule")
	public ResponseEntity<ResourceDataEntity> accessModule(@RequestBody StatementEntity statement, @RequestParam String token) throws BaseException {
		this.statementAccessModuleService.setParams(statement, token);
		this.resourceData.setMap(this.statementAccessModuleService.execute());

		return ResponseEntity.ok(this.resourceData);
	}

	@PostMapping(value = "/accessEdition")
	public ResponseEntity<ResourceDataEntity> accessEdition(@RequestBody StatementEntity statement, @RequestParam String token) throws BaseException {
		this.statementAccessEditionService.setParams(statement, token);
		this.resourceData.setMap(this.statementAccessEditionService.execute());

		return ResponseEntity.ok(this.resourceData);
	}

	@PostMapping(value = "/executeSearch")
	public ResponseEntity<ResourceDataEntity> executeSearch(@RequestBody StatementEntity statement, @RequestParam String token) throws BaseException {
		this.statementExecuteSearchService.setParams(statement, token);
		this.resourceData.setMap(this.statementExecuteSearchService.execute());

		return ResponseEntity.ok(this.resourceData);
	}

	@PostMapping(value = "/executeEdition")
	public ResponseEntity<ResourceDataEntity> executeEdition(@RequestBody StatementEntity statement, @RequestParam String token) throws BaseException {
		this.statementExecuteEditionService.setParams(statement, token);
		this.resourceData.setMap(this.statementExecuteEditionService.execute());

		return ResponseEntity.ok(this.resourceData);
	}

	@PostMapping(value = "/executeExclusion")
	public ResponseEntity<ResourceDataEntity> executeExclusion(@RequestBody StatementEntity statement, @RequestParam String token) throws BaseException {
		this.statementExecuteExclusionService.setParams(statement, token);
		this.resourceData.setMap(this.statementExecuteExclusionService.execute());

		return ResponseEntity.ok(this.resourceData);
	}

	@PostMapping(value = "/executeImport")
	public ResponseEntity<ResourceDataEntity> executeRegistration(@RequestBody StatementEntity statement, @RequestParam String token) throws BaseException {
		this.statementExecuteImportService.setParams(statement, token);
		this.resourceData.setMap(this.statementExecuteImportService.execute());

		return ResponseEntity.ok(this.resourceData);
	}
}