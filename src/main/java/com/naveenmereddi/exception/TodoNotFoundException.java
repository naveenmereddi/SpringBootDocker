package com.naveenmereddi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TodoNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 4008712015866409435L;

	public TodoNotFoundException(String todoId) {
		super("Todo not found:" + todoId);
	}

}
