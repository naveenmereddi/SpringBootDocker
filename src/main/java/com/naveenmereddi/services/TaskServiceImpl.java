package com.naveenmereddi.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.naveenmereddi.exception.TaskNotFoundException;
import com.naveenmereddi.exception.UserNotFoundException;
import com.naveenmereddi.models.dao.StatusDao;
import com.naveenmereddi.models.dao.TaskDao;
import com.naveenmereddi.models.dao.UserDao;
import com.naveenmereddi.models.entity.Status;
import com.naveenmereddi.models.entity.Task;
import com.naveenmereddi.models.entity.User;
import com.naveenmereddi.util.SearchCriteria;
import com.naveenmereddi.util.TaskStatus;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;

	@Autowired
	private StatusDao statusDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public Iterable<Task> findAll() {
		return taskDao.findAll();
	}

	@Override
	@Transactional
	@ExceptionHandler(value=UserNotFoundException.class)
	public Task createTask(String taskName, String description, String userName, String statusType) {
		User user = findUserByName(userName);
		if(user == null) {
			throw new UserNotFoundException(userName);
		}
		Status status = findStatusIdByName(statusType);
		Task task = new Task(taskName, description, user, status);
		return taskDao.save(task);
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
	@Transactional
	@ExceptionHandler(value=TaskNotFoundException.class)
	public Task findTaskById(String id) {
		Task task;
		try {
			Long taskId = new Long(id);
			task = taskDao.findOne(taskId);
		}
		catch(Exception e) {
			task = null;
		}

		if(task == null) {
			throw new TaskNotFoundException(id);
		}
		return task;
	}

	@Override
	@Transactional
	public Task updateTask(String taskId, String taskName, String description, String status) {
		try {
			Task task = findTaskById(taskId);
			task.setName(taskName);
			task.setDescription(description);
			TaskStatus tStatus = TaskStatus.getTaskStatusByType(status);
			Status statusDb = statusDao.findOne((long) tStatus.getCode());
			task.setStatus(statusDb);
			return taskDao.save(task);
		}
		catch(Exception ex) {
			return null;
		}
	}

	@Override
	public Iterable<Task> findTaskByStatus(String statusType) {
		TaskStatus status = TaskStatus.getTaskStatusByType(statusType);
		if(status != null) {
			Status stts = statusDao.findOne((long) status.getCode());
			Iterable<Task> tasksByStatus = taskDao.findTaskByStatus(stts);
			return tasksByStatus;
		}
		return new ArrayList<Task>();
	}

	@Override
	public List<Task> searchTask(List<SearchCriteria> params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Task> query = builder.createQuery(Task.class);
		Root<Task> r = query.from(Task.class);

		Predicate predicate = builder.conjunction();
		for(SearchCriteria param: params) {
			if(param.getOperation().equalsIgnoreCase("!")) {
				TaskStatus tStatus = TaskStatus.getTaskStatusByType(param.getValue().toString());
				if(tStatus != null) {
					Status status = statusDao.findOne((long) tStatus.getCode());
					predicate = builder.and(predicate, builder.notEqual(r.get(param.getKey()), status));
				}
			}
		}
		query.where(predicate);
		List<Task> result = entityManager.createQuery(query).getResultList();
		return result;
	}




}
