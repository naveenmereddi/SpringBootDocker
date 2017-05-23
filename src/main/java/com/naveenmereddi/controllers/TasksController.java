package com.naveenmereddi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.naveenmereddi.models.entity.Task;
import com.naveenmereddi.services.TaskManager;
import com.naveenmereddi.services.TaskService;
import com.naveenmereddi.util.Constants;
import com.naveenmereddi.util.SearchCriteria;

@RestController
@RequestMapping("/tasks")
@EnableAutoConfiguration
public class TasksController {

	@Autowired
	TaskService taskService;

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	ResponseEntity<Object> createTask(@RequestBody @Valid com.naveenmereddi.models.domain.Task request) {
		Task task = taskService.createTask(request.getName(), request.getDescription(), request.getUser().getUserName(), request.getStatusType());
		ResponseEntity<Object> res = new ResponseEntity<Object>(TaskManager.toDomainTask(task), HttpStatus.CREATED);
		return res;
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	com.naveenmereddi.models.domain.Task updateTask(@PathVariable("id") String id, @RequestBody @Valid com.naveenmereddi.models.domain.Task request) {
		Task task = taskService.updateTask(id, request.getName(), request.getDescription(), request.getStatusType());
		return TaskManager.toDomainTask(task);
	}

	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	Iterable<com.naveenmereddi.models.domain.Task> getAllTasks() {
		List<com.naveenmereddi.models.domain.Task> domainTasksList = TaskManager.getAllUsers(taskService.findAll());
		return domainTasksList;
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	@ResponseBody
	com.naveenmereddi.models.domain.Task getTaskByIdOrName(@PathVariable("id") String id) {
		return TaskManager.toDomainTask(taskService.findTaskById(id));
	}

	/**
	 * Usage:
	 * 1. To retrieve all tasks that do not match a specific task status.
	 * http://localhost:8080/tasks/filterBy?status=!In Progress
	 * 2. To retrieve only those tasks that match a specific task status.
	 * or http://localhost:8080/tasks/filterBy?status=In Progress
	 * @param taskStatus
	 * @return
	 */
	@RequestMapping(value="/filterBy",method=RequestMethod.GET)
	@ResponseBody
	Iterable<com.naveenmereddi.models.domain.Task> getUsersByTaskStatus(@RequestParam(required=false, value="status") String taskStatus) {

		List<SearchCriteria> params = new ArrayList<SearchCriteria>();
		Pattern pattern = Pattern.compile(Constants.REGEX);
		if(taskStatus != null) {
			Matcher matcher = pattern.matcher(taskStatus);
			while(matcher.find()) {
				params.add(new SearchCriteria("status",matcher.group(1),matcher.group(2)));
				List<Task> filteredTasksList = taskService.searchTask(params);
				return TaskManager.getAllUsers(filteredTasksList);
			}
		}
		return TaskManager.getAllUsers(taskService.findTaskByStatus(taskStatus));
	}
}
