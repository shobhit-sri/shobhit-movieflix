package io.egen.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.dto.LoginResponse;
import io.egen.api.dto.UserDto;
import io.egen.api.dto.UserLogin;
import io.egen.api.entity.User;
import io.egen.api.exception.BadRequestException;
import io.egen.api.exception.EntityNotFoundException;
import io.egen.api.mapper.UserMapper;
import io.egen.api.repository.UserRepository;
import io.egen.api.service.UserService;
import io.egen.api.util.ApplicationConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private  UserRepository repository;
	
	@Autowired
	private UserMapper mapper;
	
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
		userDto.setRole(ApplicationConstants.ROLE_USER);
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
	
	public LoginResponse authenticate(final UserLogin login) {
		if (login.email == null || repository.findByEmail(login.email) == null 
								|| !repository.findByEmail(login.email).getPassword().equals(login.password)){ 
			throw new EntityNotFoundException("Invalid username or password");
        }
		String role = repository.findByEmail(login.email).getRole();
        return new LoginResponse(Jwts.builder().setSubject(login.email)
            .claim("roles", role).setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, ApplicationConstants.SECRET_KEY).compact(), role);
	}

}