package com.naveenmereddi.services;

import com.naveenmereddi.models.entity.User;

public interface UserService {

	/**
	 * Find all the users in the system
	 * @return
	 */
	Iterable<User> findAll();

	/**
	 * Find a user based on their id or name
	 * @param idOrUserName
	 * @return
	 */
	User findByIdOrUserName(String idOrUserName);

	/**
	 * Save the user
	 * @param userName
	 * @return
	 */
	User createUser(String userName);

	/**
	 * Update the user name
	 * @param id
	 * @param userName
	 * @return
	 */
	User updateUser(Long id, String userName);

	/**
	 * Deletes the user with the first matching user name
	 * @param userName
	 * @return
	 */
	void deleteUser(String userName);

}