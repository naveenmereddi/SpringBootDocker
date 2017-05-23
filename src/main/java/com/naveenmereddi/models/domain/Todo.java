package com.naveenmereddi.models.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"id",
	"tasks"
})
public class Todo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -951020653455196496L;

	private Long id;

	@JsonProperty("tasks")
	private List<Task> tasks = new ArrayList<Task>();

	public Todo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 *
	 * @param tasks
	 */
	public Todo(List<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 *
	 * @return
	 * The tasks
	 */
	@JsonProperty("tasks")
	public List<Task> getTasks() {
		return tasks;
	}

	/**
	 *
	 * @param tasks
	 * The tasks
	 */
	@JsonProperty("tasks")
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Todo [tasks=");
		builder.append(tasks);
		builder.append("]");
		return builder.toString();
	}

}
