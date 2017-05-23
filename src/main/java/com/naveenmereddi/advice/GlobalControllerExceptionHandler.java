package com.naveenmereddi.advice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.naveenmereddi.exception.CustomError;
import com.naveenmereddi.exception.CustomErrorMessage;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	CustomErrorMessage handleException(MethodArgumentNotValidException ex) {
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
		List<CustomError> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
		for (FieldError fieldError : fieldErrors) {
			errors.add(new CustomError(fieldError.getField(),fieldError.getDefaultMessage()));
		}
		for (ObjectError objectError : globalErrors) {
			errors.add(new CustomError(objectError.getObjectName(),objectError.getDefaultMessage()));
		}
		return new CustomErrorMessage(errors);
	}

	@ResponseStatus(HttpStatus.CONFLICT)  // 409
	@ExceptionHandler(DataIntegrityViolationException.class)
	public void handleConflict() {
	}

	@ExceptionHandler({SQLException.class,DataAccessException.class})
	public CustomErrorMessage databaseError() {
		CustomError cerr = new CustomError("1001","Database error occured");
		return new CustomErrorMessage(cerr);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ResponseBody
	CustomErrorMessage getErrorMessage(HttpMediaTypeNotSupportedException ex) {
		String code = "Unsupported content types: " + ex.getContentType();
		String message = "Supported content types: " + ex.getSupportedMediaTypes();
		return new CustomErrorMessage(new CustomError(code, message));
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	CustomErrorMessage getErrorMessage(HttpMessageNotReadableException ex) {
		Throwable mostSpecificCause = ex.getMostSpecificCause();
		if (mostSpecificCause != null) {
			String exceptionName = mostSpecificCause.getClass().getName();
			String message = mostSpecificCause.getMessage();
			return new CustomErrorMessage(new CustomError(exceptionName, message));
		}
		return new CustomErrorMessage(new CustomError(ex.getMessage(),ex.getMessage()));
	}

}
