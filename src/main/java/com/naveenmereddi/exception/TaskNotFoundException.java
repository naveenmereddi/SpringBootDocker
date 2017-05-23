package com.naveenmereddi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -8504502041643537484L;

	public TaskNotFoundException(String taskId) {
		super("Could not find the task:" + taskId);
	}

}
