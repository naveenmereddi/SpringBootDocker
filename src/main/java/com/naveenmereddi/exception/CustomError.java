/**
 *
 */
package com.naveenmereddi.exception;

import java.io.Serializable;

public class CustomError implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5089373751156494557L;
	private String code;
	private String message;

	public CustomError(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
