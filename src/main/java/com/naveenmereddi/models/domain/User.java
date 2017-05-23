package com.naveenmereddi.models.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"userName"
})
public class User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5420678257365688229L;
	@NotBlank(message="User Name is required")
	@JsonProperty("userName")
	private String userName;

	public User() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
