package com.naveenmereddi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.naveenmereddi.exception.UserNotFoundException;
import com.naveenmereddi.models.dao.UserDao;
import com.naveenmereddi.models.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public Iterable<User> findAll() {
		return userDao.findAll();
	}

	@Override
	@Transactional
	public User findByIdOrUserName(String idOrUserName) {
		User user;

		try {
			Long id = new Long(idOrUserName);
			user = userDao.findOne(id);
		} catch (Exception e) {
			user = null;
		}

		if (user == null) {
			user = userDao.findByUserName(idOrUserName);
		}

		return user;
	}

	@Override
	@Transactional
	public User createUser(String userName) {
		User user = new User(userName);
		return userDao.save(user);
	}

	@Override
	@Transactional
	public User updateUser(Long id, String userName) {
		try {
			User user = userDao.findOne(id);
			user.setUserName(userName);
			return userDao.save(user);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@ExceptionHandler(value=UserNotFoundException.class)
	public void deleteUser(String userName) {
		User user = findByIdOrUserName(userName);
		if(user == null) {
			throw new UserNotFoundException(userName);
		}
		userDao.delete(user);
	}
}