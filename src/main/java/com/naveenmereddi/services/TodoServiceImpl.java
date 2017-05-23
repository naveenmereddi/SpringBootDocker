package com.naveenmereddi.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.naveenmereddi.exception.TodoNotFoundException;
import com.naveenmereddi.exception.UserNotFoundException;
import com.naveenmereddi.models.dao.StatusDao;
import com.naveenmereddi.models.dao.TodoDao;
import com.naveenmereddi.models.dao.UserDao;
import com.naveenmereddi.models.entity.Status;
import com.naveenmereddi.models.entity.Todo;
import com.naveenmereddi.models.entity.User;
import com.naveenmereddi.util.TaskStatus;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoDao todoDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private StatusDao statusDao;

	@Override
	@Transactional
	public Iterable<Todo> findAll() {
		return todoDao.findAll();
	}

	@Override
	@Transactional
	@ExceptionHandler(value=UserNotFoundException.class)
	public Todo createTodo(com.naveenmereddi.models.domain.Todo domainTodo) {
		com.naveenmereddi.models.entity.Todo entityTodo = buildEntityTodo(domainTodo);
		return todoDao.save(entityTodo);
	}

	private com.naveenmereddi.models.entity.Todo buildEntityTodo(com.naveenmereddi.models.domain.Todo domainTodo) {
		com.naveenmereddi.models.entity.Todo entityTodo = new com.naveenmereddi.models.entity.Todo();
		List<com.naveenmereddi.models.entity.Task> entityTasksList = new ArrayList<com.naveenmereddi.models.entity.Task>();
		List<com.naveenmereddi.models.domain.Task> domainTodosList = domainTodo.getTasks();
		populateTasksList(entityTasksList, domainTodosList);
		entityTodo.setTasksList(entityTasksList);
		return entityTodo;
	}

	private void populateTasksList(List<com.naveenmereddi.models.entity.Task> entityTasksList,
			List<com.naveenmereddi.models.domain.Task> domainTodosList) {
		for(com.naveenmereddi.models.domain.Task dtask: domainTodosList) {
			com.naveenmereddi.models.entity.Task tk = new com.naveenmereddi.models.entity.Task();
			tk.setName(dtask.getName());
			tk.setDescription(dtask.getDescription());
			User usr = findUserByName(dtask.getUser().getUserName());
			tk.setUser(usr);
			Status status = findStatusIdByName(dtask.getStatusType());
			tk.setStatus(status);
			entityTasksList.add(tk);
		}
	}

	@Transactional
	private Status findStatusIdByName(String statusType) {
		TaskStatus tStatus = TaskStatus.getTaskStatusByType(statusType);
		Status status = statusDao.findOne((long) tStatus.getCode());
		return status;
	}

	@Transactional
	public User findUserByName(String userName) {
		User user = userDao.findByUserName(userName);
		return user;
	}

	@Override
	@ExceptionHandler(TodoNotFoundException.class)
	public Todo updateTodo(String id, com.naveenmereddi.models.domain.Todo todo) {
		Todo todoFromDB = findOne(id);
		if(todoFromDB == null) {
			throw new TodoNotFoundException(id);
		}
		List<com.naveenmereddi.models.entity.Task> entityTasksList = new ArrayList<com.naveenmereddi.models.entity.Task>();
		List<com.naveenmereddi.models.domain.Task> domainTodosList = todo.getTasks();
		populateTasksList(entityTasksList, domainTodosList);
		todoFromDB.setTasksList(entityTasksList);
		todoFromDB = todoDao.save(todoFromDB);
		return todoFromDB;
	}


	@Override
	public void deleteTodo(String todoId) {
		todoDao.delete(new Long(todoId));
	}

	@Override
	public Todo findOne(String todoId) {
		return todoDao.findOne(new Long(todoId));
	}

}
