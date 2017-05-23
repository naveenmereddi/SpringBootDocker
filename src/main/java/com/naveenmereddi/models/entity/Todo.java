package com.naveenmereddi.models.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="todos")
public class Todo {

	private Long todoId;

	private List<Task> tasksList;

	public Todo() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TODO_ID")
	public Long getTodoId() {
		return todoId;
	}

	public void setTodoId(Long todoId) {
		this.todoId = todoId;
	}

	@OneToMany(fetch=FetchType.LAZY, targetEntity=Task.class, cascade=CascadeType.ALL)
	@JoinTable(name="TODOS_TASKS",
	joinColumns={@JoinColumn(name="TODO_ID_FK", referencedColumnName="TODO_ID")},
	inverseJoinColumns={@JoinColumn(name="TASK_ID_FK", referencedColumnName="TASK_ID")})

	public List<Task> getTasksList() {
		return tasksList;
	}

	public void setTasksList(List<Task> tasksList) {
		this.tasksList = tasksList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((tasksList == null) ? 0 : tasksList.hashCode());
		result = (prime * result) + ((todoId == null) ? 0 : todoId.hashCode());
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
		Todo other = (Todo) obj;
		if (tasksList == null) {
			if (other.tasksList != null) {
				return false;
			}
		} else if (!tasksList.equals(other.tasksList)) {
			return false;
		}
		if (todoId == null) {
			if (other.todoId != null) {
				return false;
			}
		} else if (!todoId.equals(other.todoId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Todo [todoId=");
		builder.append(todoId);
		builder.append(", tasksList=");
		builder.append(tasksList);
		builder.append("]");
		return builder.toString();
	}

}
