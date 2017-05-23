/**
 *
 */
package com.naveenmereddi.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author nm84573
 *
 */
public class CustomErrorMessage implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2639635674993114379L;
	private List<CustomError> errors = new ArrayList<CustomError>();

	public List<CustomError> getErrors() {
		return errors;
	}

	public void setErrors(List<CustomError> errors) {
		this.errors = errors;
	}

	public CustomErrorMessage(List<CustomError> errorsList) {
		errors = errorsList;
	}

	public CustomErrorMessage(CustomError error) {
		errors.add(error);
		Collections.singletonList(errors);
	}

}
