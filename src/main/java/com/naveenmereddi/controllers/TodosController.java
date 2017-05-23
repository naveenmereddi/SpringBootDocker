package com.naveenmereddi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.naveenmereddi.services.TodoManager;
import com.naveenmereddi.services.TodoService;

@RestController
@RequestMapping("/todos")
@EnableAutoConfiguration
public class TodosController {

	@Autowired
	TodoService todoService;

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	com.naveenmereddi.models.domain.Todo createTodo(@RequestBody @Valid com.naveenmereddi.models.domain.Todo todo) {
		com.naveenmereddi.models.entity.Todo todoEntity = todoService.createTodo(todo);
		return TodoManager.toDomainTodo(todoEntity);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	com.naveenmereddi.models.domain.Todo updateTodo(@PathVariable("id") String id, @RequestBody @Valid com.naveenmereddi.models.domain.Todo todo) {
		com.naveenmereddi.models.entity.Todo todoEntity = todoService.updateTodo(id, todo);
		return TodoManager.toDomainTodo(todoEntity);
	}

	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	Iterable<com.naveenmereddi.models.domain.Todo> getAllTodos() {
		return TodoManager.getAllTodos(todoService.findAll());
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	com.naveenmereddi.models.domain.Todo getTodoById(@PathVariable("id") String todoId) {
		com.naveenmereddi.models.entity.Todo todo = todoService.findOne(todoId);
		return TodoManager.toDomainTodo(todo);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	void deleteTodo(@PathVariable("id") String todoId) {
		todoService.deleteTodo(todoId);
	}
}
