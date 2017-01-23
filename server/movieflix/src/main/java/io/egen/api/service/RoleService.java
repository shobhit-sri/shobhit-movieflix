package io.egen.api.service;

import java.util.List;

import io.egen.api.dto.RoleDto;

public interface RoleService {
	public List<RoleDto> findAll();
	
	public RoleDto findOne(String id);
	
	public RoleDto create(RoleDto roleDto);
}
