package com.naveenmereddi.util;

public enum TaskStatus {
	NOT_STARTED(1, "Not Started"), IN_PROGRESS(2, "In Progress"), COMPLETE(3, "Complete");

	private int code;
	private String statusType;

	public int getCode() {
		return code;
	}

	private TaskStatus(int id, String status) {
		this.code = id;
		this.statusType = status;
	}

	public String getStatus() {
		return statusType;
	}

	/**
	 * Given the status type, return the code
	 * @param statusType
	 * @return
	 */
	public static TaskStatus getTaskStatusByType(String statusType) {
		TaskStatus[] values = TaskStatus.values();
		for (TaskStatus taskStatus : values) {
			if(taskStatus.getStatus().equalsIgnoreCase(statusType)) {
				return taskStatus;
			}
		}
		return null;
	}

	/**
	 * Given the code, return the status type
	 * @param code
	 * @return
	 */
	public static TaskStatus getStatusTypeByCode(int code) {
		TaskStatus[] values = TaskStatus.values();
		for (TaskStatus taskStatus : values) {
			if(taskStatus.getCode() == code) {
				return taskStatus;
			}
		}
		return null;
	}

}

