package com.naveenmereddi.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.naveenmereddi.models.entity.Status;

public interface StatusDao extends CrudRepository<Status, Long> {

}
