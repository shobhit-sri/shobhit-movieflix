package io.egen.api.service;

import java.util.List;

import io.egen.api.dto.UserDto;

public interface UserService {

	public List<UserDto> findAll();

	public UserDto findOne(String id);
	
	public UserDto findByEmail(String email);

	public UserDto create(UserDto emp);

	public UserDto update(String id, UserDto userDto);

	public void delete(String id);
}
