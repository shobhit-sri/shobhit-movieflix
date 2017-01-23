package io.egen.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.dto.RoleDto;
import io.egen.api.dto.UserDto;
import io.egen.api.entity.User;
import io.egen.api.exception.BadRequestException;
import io.egen.api.exception.EntityNotFoundException;
import io.egen.api.mapper.RoleMapper;
import io.egen.api.mapper.UserMapper;
import io.egen.api.repository.UserRepository;
import io.egen.api.service.RoleService;
import io.egen.api.service.UserService;
import io.egen.api.util.ApplicationConstants;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private  UserRepository repository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<UserDto> findAll() {
		List<User> users = repository.findAll();
		List<UserDto> userDtos = users.stream().map(u -> mapper.getDtoFromEntity(u)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto findOne(String id) {
		User user = repository.findOne(id);
		if(user == null){
			throw new EntityNotFoundException("User not found");
		}
		return mapper.getDtoFromEntity(user);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto findByEmail(String email) {
		User user = repository.findByEmail(email);
		if(user == null){
			throw new EntityNotFoundException("User with this email is not found");
		}
		return mapper.getDtoFromEntity(user);
	}

	@Override
	@Transactional
	public UserDto create(UserDto userDto) {
		User user = repository.findByEmail(userDto.getEmail());
		if (user != null) {
			throw new BadRequestException("User with this email already exists");
		}
		//Set role to USER
		RoleDto roleDto = roleService.findAll().stream()
									 .filter(r -> r.getType().equalsIgnoreCase(ApplicationConstants.ROLE_USER))
									 .findFirst()
									 .get();
		userDto.setRole(roleMapper.getEntityFromDto(roleDto));
		user = mapper.getEntityFromDto(userDto, true);
		return mapper.getDtoFromEntity(repository.create(user));
	}

	@Override
	@Transactional
	public UserDto update(String id, UserDto userDto) {
		User user = repository.findOne(id);
		if (user == null) {
			throw new EntityNotFoundException("User not found");
		}
		user = mapper.getEntityFromDto(userDto);
		return mapper.getDtoFromEntity(repository.update(user));
	}

	@Override
	@Transactional
	public void delete(String id) {
		User user = repository.findOne(id);
		if (user == null) {
			throw new EntityNotFoundException("User not found");
		}
		repository.delete(user);
	}

}