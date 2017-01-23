package io.egen.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import io.egen.api.dto.RoleDto;
import io.egen.api.entity.Role;

@Component
public class RoleMapper extends ModelMapper{

	public RoleDto getDtoFromEntity(Role role){
		return map(role, RoleDto.class);
	}
	
	public Role getEntityFromDto(RoleDto roleDto){
		return map(roleDto, Role.class);
	}
	
	public Role getEntityFromDto(RoleDto roleDto, boolean isNew){
		Role role = getEntityFromDto(roleDto);
		role.setId(new Role().getId());
		return role;
	}
}
