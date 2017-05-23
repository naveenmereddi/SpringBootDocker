package com.naveenmereddi.services;

import java.util.List;

import com.naveenmereddi.models.entity.Task;
import com.naveenmereddi.util.SearchCriteria;

public interface TaskService {

	/**
	 * Find all tasks in the system
	 * @return
	 */
	Iterable<Task> findAll();

	/**
	 * Save the task
	 * @param taskName
	 * @param description
	 * @param userName
	 * @param statusType
	 * @return
	 */
	Task createTask(String taskName, String description, String userName, String statusType);

	/**
	 * Retrieve a task based on it's id
	 * @param id
	 * @return
	 */
	Task findTaskById(String id);

	/**
	 * Update the task's name, description and status, given it's id.
	 * @param taskId
	 * @param taskName
	 * @param description
	 * @param statusType
	 * @return
	 */
	Task updateTask(String taskId, String taskName, String description, String statusType);

	/**
	 * Fetch a task by it's status
	 * @param statusType
	 * @return
	 */
	Iterable<Task> findTaskByStatus(String statusType);

	/**
	 *
	 * @param params
	 * @return
	 */
	List<Task> searchTask(List<SearchCriteria> params);

}