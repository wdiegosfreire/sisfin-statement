package br.com.dfdevforge.sisfinstatement.exceptions;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.sisfinstatement.commons.exceptions.HttpStatusInternalServerError;

public class ChildrenInformationFoundException extends BaseException implements HttpStatusInternalServerError {
	private static final long serialVersionUID = 1L;

	public ChildrenInformationFoundException() {
		super("Children Information Found", "There are children information associated with this record.");
	}
}