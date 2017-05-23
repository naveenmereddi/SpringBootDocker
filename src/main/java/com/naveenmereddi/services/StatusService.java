package com.naveenmereddi.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.naveenmereddi.models.dao.StatusDao;
import com.naveenmereddi.models.entity.Status;

public class StatusService {

	@Autowired
	StatusDao statusDao;

	public Iterable<Status> findAll() {
		return statusDao.findAll();
	}

}
