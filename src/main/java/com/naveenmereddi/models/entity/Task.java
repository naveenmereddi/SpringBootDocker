package com.naveenmereddi.models.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tasks")
public class Task {

	private Long taskId;
	private String name;
	private String description;
	private User user;
	private Status status;

	public Task() {
	}

	public Task(String name, String description, User usr, Status status) {
		super();
		this.name = name;
		this.description = description;
		this.user = usr;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TASK_ID")
	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToOne(fetch=FetchType.EAGER,targetEntity=Status.class)
	@JoinColumn(name="STATUS_ID",unique=false)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, targetEntity=User.class)
	@JoinColumn(name="USER_ID", unique=false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((description == null) ? 0 : description.hashCode());
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		result = (prime * result) + ((status == null) ? 0 : status.hashCode());
		result = (prime * result) + ((taskId == null) ? 0 : taskId.hashCode());
		result = (prime * result) + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Task other = (Task) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (taskId == null) {
			if (other.taskId != null) {
				return false;
			}
		} else if (!taskId.equals(other.taskId)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Task [taskId=");
		builder.append(taskId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", status=");
		builder.append(status);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}


}
