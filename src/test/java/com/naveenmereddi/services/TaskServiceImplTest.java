package com.naveenmereddi.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.naveenmereddi.models.dao.StatusDao;
import com.naveenmereddi.models.dao.TaskDao;
import com.naveenmereddi.models.dao.UserDao;
import com.naveenmereddi.models.entity.Status;
import com.naveenmereddi.models.entity.Task;
import com.naveenmereddi.models.entity.User;
import com.naveenmereddi.util.TaskStatus;

public class TaskServiceImplTest {

	@Mock
	private TaskDao taskDao;

	@Mock
	private UserDao userDao;

	@Mock
	private StatusDao statusDao;

	@InjectMocks
	private TaskService taskService = new TaskServiceImpl();

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	private long taskId = 1l;
	private long userId = 1l;
	private String taskName = "Task1";
	private String userName = "User1";
	private User user;
	private Task task;
	private Status status;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		user = new User(userName);
		user.setId(userId);
		status = new Status(TaskStatus.IN_PROGRESS.getStatus());
		task = new Task(taskName, taskName, user, status);
		task.setTaskId(taskId);
	}

	@Test
	public void testCreateTask() {
		Mockito.when(taskDao.save(Mockito.any(com.naveenmereddi.models.entity.Task.class))).thenReturn(task);
		Mockito.when(userDao.findByUserName(Mockito.any(String.class))).thenReturn(user);
		Mockito.when(statusDao.findOne(Mockito.any(Long.class))).thenReturn(status);

		Task t = taskService.createTask(taskName, taskName, userName, status.getStatusType());

		Mockito.verify(taskDao,Mockito.times(1)).save(Mockito.any(Task.class));
		assertNotNull(t);
		assertNotNull(t.getTaskId());
		assertEquals(taskName, t.getName());
	}

	@Test
	public void testUpdateTask() {
		String newStatus = TaskStatus.COMPLETE.getStatus();
		Status status = new Status(newStatus);
		Task updatedTask = new Task(taskName, taskName, user, status);
		updatedTask.setTaskId(2l);

		Mockito.when(statusDao.findOne(Mockito.any(Long.class))).thenReturn(status);
		Mockito.when(taskDao.save(Mockito.any(Task.class))).thenReturn(updatedTask);
		Mockito.when(taskDao.findOne(Mockito.any(Long.class))).thenReturn(updatedTask);
		Task task = taskService.updateTask("2",taskName, taskName, newStatus);

		Mockito.verify(taskDao,Mockito.times(1)).save(Mockito.any(Task.class));

		assertNotNull(task);
		assertNotNull(task.getTaskId());
		assertNotNull(task.getStatus());

	}

	@Test
	public void testFindTaskById() {
		Mockito.when(taskDao.findOne(Mockito.any(Long.class))).thenReturn(task);
		Task foundTask = taskService.findTaskById("1");

		Mockito.verify(taskDao,Mockito.times(1)).findOne(Mockito.any(Long.class));

		assertNotNull(foundTask);
		assertNotNull(foundTask.getTaskId());
	}

	@Test
	public void testFindUserByName() {
		Mockito.when(userDao.findByUserName(Mockito.any(String.class))).thenReturn(user);
		User usr = userService.findByIdOrUserName("1");

		Mockito.verify(userDao, Mockito.times(1)).findOne(Mockito.any(Long.class));

		assertNotNull(usr);
	}


}
