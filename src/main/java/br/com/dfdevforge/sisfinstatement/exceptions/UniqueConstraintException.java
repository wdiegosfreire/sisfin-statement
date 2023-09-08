package br.com.dfdevforge.sisfinstatement.exceptions;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.sisfinstatement.commons.exceptions.HttpStatusInternalServerError;

public class UniqueConstraintException extends BaseException implements HttpStatusInternalServerError {
	private static final long serialVersionUID = 1L;

	public UniqueConstraintException() {
		super("Unique Constraint Violated", "This information already exists. Review the data and try again.");
	}
}