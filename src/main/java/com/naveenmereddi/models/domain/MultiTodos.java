package com.naveenmereddi.models.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MultiTodos {
	@JsonProperty("todos")
	private List<Todo> todosList;

	public List<Todo> getTodosList() {
		return todosList;
	}

	public void setTodosList(List<Todo> todosList) {
		this.todosList = todosList;
	}
}
