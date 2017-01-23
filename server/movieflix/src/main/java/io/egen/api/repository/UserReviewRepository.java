package io.egen.api.repository;

import java.util.List;

import io.egen.api.entity.UserReview;

public interface UserReviewRepository {
	
	public List<UserReview> findAll();
	
	public UserReview findOne(String id);
	
	public List<UserReview> findByUser(String userId);
	
	public List<UserReview> findByMovie(String movieId);
	
	public UserReview create(UserReview userReview);
	
	public UserReview update(UserReview userReview);
	
	public void delete(UserReview userReview);
}
