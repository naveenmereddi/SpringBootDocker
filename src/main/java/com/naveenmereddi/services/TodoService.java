package com.naveenmereddi.services;

import com.naveenmereddi.models.entity.Todo;

public interface TodoService {

	Iterable<Todo> findAll();

	Todo createTodo(com.naveenmereddi.models.domain.Todo todo);

	Todo updateTodo(String id, com.naveenmereddi.models.domain.Todo todo);

	void deleteTodo(String todoId);

	Todo findOne(String todoId);

}
