package com.naveenmereddi.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="status_type")
public class Status {

	private Long statusId;
	private String statusType;

	public Status(String statusType) {
		this.statusType = statusType;
	}

	public Status() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="STATUS_ID")
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	@Column(name="STATUS_TYPE")
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((statusId == null) ? 0 : statusId.hashCode());
		result = (prime * result) + ((statusType == null) ? 0 : statusType.hashCode());
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
		Status other = (Status) obj;
		if (statusId == null) {
			if (other.statusId != null) {
				return false;
			}
		} else if (!statusId.equals(other.statusId)) {
			return false;
		}
		if (statusType == null) {
			if (other.statusType != null) {
				return false;
			}
		} else if (!statusType.equals(other.statusType)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Status [statusId=");
		builder.append(statusId);
		builder.append(", statusType=");
		builder.append(statusType);
		builder.append("]");
		return builder.toString();
	}

}
