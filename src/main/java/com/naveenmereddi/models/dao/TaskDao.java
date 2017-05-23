package com.naveenmereddi.models.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.naveenmereddi.models.entity.Status;
import com.naveenmereddi.models.entity.Task;

@Transactional
public interface TaskDao extends CrudRepository<Task, Long>{

	/**
	 *
	 * @param taskId
	 * @return
	 */
	Task findTaskBytaskId(String taskId);

	/**
	 * Find task by it's status
	 * @param statusType
	 * @return
	 */
	Iterable<Task> findTaskByStatus(Status status);

}
