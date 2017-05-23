package com.naveenmereddi.services;

import java.util.ArrayList;
import java.util.List;

import com.naveenmereddi.models.entity.Task;

public final class TodoManager {

	public static com.naveenmereddi.models.domain.Todo toDomainTodo(com.naveenmereddi.models.entity.Todo todoEntity) {
		com.naveenmereddi.models.domain.Todo domainTodo = new com.naveenmereddi.models.domain.Todo();
		if(todoEntity !=null) {
			List<com.naveenmereddi.models.domain.Task> domainTasksList = new ArrayList<com.naveenmereddi.models.domain.Task>();
			List<Task> entityTasksList = todoEntity.getTasksList();
			domainTodo.setId(todoEntity.getTodoId());
			for(Task t: entityTasksList) {
				com.naveenmereddi.models.domain.Task dt = new com.naveenmereddi.models.domain.Task();
				com.naveenmereddi.models.domain.User usr = new com.naveenmereddi.models.domain.User();
				dt.setId(t.getTaskId());
				dt.setName(t.getName());
				dt.setDescription(t.getDescription());
				usr.setUserName(t.getUser().getUserName());
				dt.setUser(usr);
				domainTasksList.add(dt);
			}
			domainTodo.setTasks(domainTasksList);
		}
		return domainTodo;
	}

	public static List<com.naveenmereddi.models.domain.Todo> getAllTodos(Iterable<com.naveenmereddi.models.entity.Todo> todosIter) {
		List<com.naveenmereddi.models.domain.Todo> todosList = new ArrayList<com.naveenmereddi.models.domain.Todo>();
		for(com.naveenmereddi.models.entity.Todo eTodo: todosIter) {
			todosList.add(toDomainTodo(eTodo));
		}
		return todosList;
	}

}
