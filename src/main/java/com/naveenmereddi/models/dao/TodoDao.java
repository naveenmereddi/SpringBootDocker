package com.naveenmereddi.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.naveenmereddi.models.entity.Todo;

public interface TodoDao extends CrudRepository<Todo, Long> {

}
