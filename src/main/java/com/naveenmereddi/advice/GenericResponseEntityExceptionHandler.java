/**
 *
 */
package com.naveenmereddi.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.naveenmereddi.exception.CustomError;
import com.naveenmereddi.exception.CustomErrorMessage;



/**
 * @author nm84573
 *
 */
@ControllerAdvice
public class GenericResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String _422 = "422";
	private static final String _415 = "415";
	private static final String _405 = "405";
	private static final String SUPPORTED_MEDIA_TYPES_ARE = "Supported Media Types are:";
	private static final String CONTENT_TYPE_SPECIFIED = "Content type specified:";
	private static final String SUPPORTED_METHODS = "Supported Methods:";
	private static final String METHOD_SPECIFIED = "Method specified:";

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String requestedMethod = METHOD_SPECIFIED + ex.getMethod();
		String supportedMethods = SUPPORTED_METHODS + ex.getSupportedHttpMethods();
		String errorCode = _405;
		String errorMessage = requestedMethod + ", " + supportedMethods;
		CustomError error = new CustomError(errorCode, errorMessage);
		CustomErrorMessage cem = new CustomErrorMessage(error);
		return new ResponseEntity<Object>(cem, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String incomingContentType = CONTENT_TYPE_SPECIFIED + ex.getContentType();
		String supportedMediaTypes = SUPPORTED_MEDIA_TYPES_ARE + ex.getSupportedMediaTypes();
		String errorCode = _415;
		String errorMessage = incomingContentType + ", " + supportedMediaTypes;
		CustomError error = new CustomError(errorCode, errorMessage);
		CustomErrorMessage cem = new CustomErrorMessage(error);
		return new ResponseEntity<Object>(cem, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return super.handleMissingPathVariable(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable mostSpecificCause = ex.getMostSpecificCause();
		CustomErrorMessage errorMessage;
		if (mostSpecificCause != null) {
			String exceptionName = mostSpecificCause.getClass().getName();
			String message = mostSpecificCause.getMessage();
			String code = _422;
			String errMessage = exceptionName + ", " + message;
			CustomError error = new CustomError(code, errMessage);
			errorMessage = new CustomErrorMessage(error);
		} else {
			errorMessage = new CustomErrorMessage(new CustomError(_422, ex.getMessage()));
		}
		return new ResponseEntity<Object>(errorMessage, headers, status);
	}

}
