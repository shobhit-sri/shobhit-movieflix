package io.egen.api.repository;

import java.util.List;

import io.egen.api.entity.Role;

public interface RoleRepository {
	public List<Role> findAll();
	
	public Role findOne(String id);
	
	public Role findByType(String type);
	
	public Role create(Role role);
}
