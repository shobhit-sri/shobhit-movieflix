package io.egen.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.dto.RoleDto;
import io.egen.api.entity.Role;
import io.egen.api.exception.BadRequestException;
import io.egen.api.exception.EntityNotFoundException;
import io.egen.api.mapper.RoleMapper;
import io.egen.api.repository.RoleRepository;
import io.egen.api.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repository;
	
	@Autowired
	private RoleMapper mapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<RoleDto> findAll() {
		List<Role> roles = repository.findAll();
		List<RoleDto> roleDtos = roles.stream().map(r -> mapper.getDtoFromEntity(r)).collect(Collectors.toList());
		return roleDtos;
	}

	@Override
	@Transactional(readOnly = true)
	public RoleDto findOne(String id) {
		Role role = repository.findOne(id);
		if (role == null) {
			throw new EntityNotFoundException("Role not found");
		}
		return mapper.getDtoFromEntity(role);
	}

	@Override
	@Transactional
	public RoleDto create(RoleDto roleDto) {
		Role role = repository.findByType(roleDto.getType());
		if (role != null) {
			throw new BadRequestException("Role already exists");
		}
		role = mapper.getEntityFromDto(roleDto, true);
		return mapper.getDtoFromEntity(repository.create(role));
	}

}
