package com.naveenmereddi.models.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.naveenmereddi.models.entity.User;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {

  User findByUserName(String userName);

}