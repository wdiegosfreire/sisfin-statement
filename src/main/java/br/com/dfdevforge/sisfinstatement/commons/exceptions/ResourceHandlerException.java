package br.com.dfdevforge.sisfinstatement.commons.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.dfdevforge.sisfinstatement.exceptions.ChildrenInformationFoundException;
import br.com.dfdevforge.sisfinstatement.exceptions.DataForEditionNotFoundException;
import br.com.dfdevforge.sisfinstatement.exceptions.DataForExclusionNotFoundException;
import br.com.dfdevforge.sisfinstatement.exceptions.RequiredFieldNotFoundException;

@ControllerAdvice
public class ResourceHandlerException {
	@ExceptionHandler({DataForEditionNotFoundException.class, DataForExclusionNotFoundException.class})
	public ResponseEntity<String> httpNotFoundExceptionHandler(HttpStatusNotFound exception, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}

	@ExceptionHandler({RequiredFieldNotFoundException.class, ChildrenInformationFoundException.class})
	public ResponseEntity<String> httpInternalServerErrorExceptionHandler(HttpStatusInternalServerError exception, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
	}
}