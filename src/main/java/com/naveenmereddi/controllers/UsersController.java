package com.naveenmereddi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.naveenmereddi.models.entity.User;
import com.naveenmereddi.services.UserService;

@RestController
@EnableAutoConfiguration
public class UsersController {

	@Autowired
	private UserService userService;

	@RequestMapping(value="/users", method=RequestMethod.GET)
	@ResponseBody
	Iterable<User> getAllUsers() {
		return userService.findAll();
	}

	@RequestMapping(value = "/users/{id}", method=RequestMethod.GET)
	@ResponseBody
	User getUserByIdOrUserName(@PathVariable("id") String idOrUserName) {
		return userService.findByIdOrUserName(idOrUserName);
	}

	@RequestMapping(value="/users", method=RequestMethod.POST)
	@ResponseBody
	User createUser(@RequestBody @Valid com.naveenmereddi.models.domain.User request) {
		return userService.createUser(request.getUserName());
	}

	@RequestMapping(value="/users/{id}", method=RequestMethod.PUT)
	@ResponseBody
	User updateUser(@PathVariable("id") Long id, @RequestBody @Valid com.naveenmereddi.models.domain.User request) {
		return userService.updateUser(id, request.getUserName());
	}

	@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	ResponseEntity<?> deleteUser(@PathVariable("id") String idOrUserName) {
		userService.deleteUser(idOrUserName);
		ResponseEntity<?> re = new ResponseEntity(null, HttpStatus.ACCEPTED);
		return re;
	}
}