package io.egen.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import io.egen.api.dto.UserReviewDto;
import io.egen.api.entity.UserReview;

@Component
public class UserReviewMapper extends ModelMapper {
	public UserReviewDto getDtoFromEntity(UserReview userReview){
		return map(userReview, UserReviewDto.class);
	}
	
	public UserReview getEntityFromDto(UserReviewDto userReviewDto){
		return map(userReviewDto, UserReview.class);
	}
	
	public UserReview getEntityFromDto(UserReviewDto userReviewDto, boolean isNew){
		UserReview userReview = getEntityFromDto(userReviewDto);
		if(isNew){
			userReview.setId(new UserReview().getId());
		}
		return userReview;
	}
}
