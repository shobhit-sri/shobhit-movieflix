package io.egen.api.service;

import java.util.List;

import io.egen.api.dto.UserReviewDto;

public interface UserReviewService {
	
	public List<UserReviewDto> findAll();
	
	public UserReviewDto findOne(String id);
	
	public List<UserReviewDto> findByUser(String userId);
	
	public List<UserReviewDto> findByMovie(String movieId);
	
	public float getAverageUserRating(String movieId);
	
	public UserReviewDto create(UserReviewDto userReviewDto);
	
	public UserReviewDto update(String id, UserReviewDto userReviewDto);
	
	public void delete(String id);
}
