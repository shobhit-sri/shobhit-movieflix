package io.egen.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import io.egen.api.dto.UserDto;
import io.egen.api.entity.User;

@Component
public class UserMapper extends ModelMapper{
	public UserDto getDtoFromEntity(User user){
		return map(user, UserDto.class);
	}
	
	public User getEntityFromDto(UserDto userDto){
		return map(userDto, User.class);
	}
	
	public User getEntityFromDto(UserDto userDto, boolean isNew){
		User user = getEntityFromDto(userDto);
		if(isNew){
			user.setId(new User().getId());
		}
		return user;
	}
}
