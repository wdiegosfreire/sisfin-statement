package br.com.dfdevforge.common.services;

import java.util.HashMap;
import java.util.Map;

import br.com.dfdevforge.common.exceptions.BaseException;

public abstract class BaseService implements CommonService {
	protected String token;
	protected Map<String, Object> resultMap = new HashMap<>();

	@Override
	public void validateUserAccess() throws BaseException {
		
	}

	@Override
	public abstract void executeBusinessRule() throws BaseException;

	@Override
	public void configureUserActions() {
		// If needed, concrete classes must implement this method.
	}

	@Override
	public Map<String, Object> returnBusinessData() {
		return this.resultMap;
	}

	/**
	 * Its purpose is to put in the resulting map any list generated by the business rule.
	 * 
	 * @param artifactName
	 * @param beanList
	 */
	protected void setArtifact(String artifactName, Object artifact) {
		this.resultMap.put(artifactName, artifact);
	}
}