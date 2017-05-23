package com.naveenmereddi.models.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"id",
	"name",
	"description",
	"status",
	"user"
})
public class Task implements Serializable {

	private static final String STATUS_TYPE_REGEX = "^(In Progress|Not Started|Complete)?$";

	/**
	 *
	 */
	private static final long serialVersionUID = 8337276608621886265L;

	private long id;

	@JsonProperty("name")
	@NotBlank(message="Task name is a required field")
	private String name;

	@JsonProperty("description")
	@NotBlank(message="Task description is a required field")
	private String description;

	@JsonProperty("status")
	@NotBlank(message="Task status is a required field")
	@Pattern(flags=Pattern.Flag.CASE_INSENSITIVE, regexp = STATUS_TYPE_REGEX)
	private String statusType;

	@JsonProperty("user")
	@NotNull
	private User user;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Task() {
	}

	/**
	 *
	 * @param taskId
	 * @param description
	 * @param name
	 * @param user
	 */
	public Task(String name, String description, String statusType, User user) {
		this.name = name;
		this.description = description;
		this.statusType = statusType;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 *
	 * @return
	 * The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 *
	 * @param name
	 * The name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return
	 * The description
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 *
	 * @param description
	 * The description
	 */
	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 *
	 * @return
	 * The user
	 */
	@JsonProperty("user")
	public User getUser() {
		return user;
	}

	/**
	 *
	 * @param user
	 * The user
	 */
	@JsonProperty("user")
	public void setUser(User user) {
		this.user = user;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Task [name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", statusType=");
		builder.append(statusType);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}

}
