package io.egen.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.api.dto.LoginResponse;
import io.egen.api.dto.UserDto;
import io.egen.api.dto.UserLogin;
import io.egen.api.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<UserDto> findAll() {
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public UserDto findOne(@PathVariable("id") String userId) {
		return service.findOne(userId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "em/{email}")
	public UserDto findByEmail(@PathVariable("email") String email) {
		return service.findByEmail(email);
	}

	@RequestMapping(method = RequestMethod.POST, value="create")
	public UserDto create(@RequestBody UserDto user) {
		return service.create(user);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public UserDto update(@PathVariable("id") String id, @RequestBody UserDto user) {
		return service.update(id, user);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "login")
    public LoginResponse login(@RequestBody final UserLogin login) {
		return service.authenticate(login);
    }

	
}