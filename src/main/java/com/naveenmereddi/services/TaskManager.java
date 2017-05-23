package com.naveenmereddi.services;

import java.util.ArrayList;
import java.util.List;

import com.naveenmereddi.models.entity.Task;
import com.naveenmereddi.util.TaskStatus;

public final class TaskManager {

	public static com.naveenmereddi.models.domain.Task toDomainTask(com.naveenmereddi.models.entity.Task persistedTask) {
		com.naveenmereddi.models.domain.Task domainTask = new com.naveenmereddi.models.domain.Task();
		com.naveenmereddi.models.domain.User user = new com.naveenmereddi.models.domain.User();
		if(persistedTask != null) {
			domainTask.setId(persistedTask.getTaskId());
			domainTask.setName(persistedTask.getName());
			domainTask.setDescription(persistedTask.getDescription());
			domainTask.setStatusType(TaskStatus.getTaskStatusByType(persistedTask.getStatus().getStatusType()).getStatus());
			user.setUserName(persistedTask.getUser().getUserName());
			domainTask.setUser(user);
		}
		return domainTask;
	}

	public static List<com.naveenmereddi.models.domain.Task> getAllUsers(Iterable<com.naveenmereddi.models.entity.Task> persistedTasks) {
		List<com.naveenmereddi.models.domain.Task> domainTasksList = new ArrayList<com.naveenmereddi.models.domain.Task>();
		for (Task task : persistedTasks) {
			domainTasksList.add(toDomainTask(task));
		}
		return domainTasksList;
	}

}
