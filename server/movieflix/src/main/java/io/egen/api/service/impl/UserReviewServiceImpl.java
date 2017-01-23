package io.egen.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.dto.UserReviewDto;
import io.egen.api.entity.UserReview;
import io.egen.api.exception.EntityNotFoundException;
import io.egen.api.mapper.UserReviewMapper;
import io.egen.api.repository.UserReviewRepository;
import io.egen.api.service.UserReviewService;

@Service
public class UserReviewServiceImpl implements UserReviewService {

	@Autowired
	private  UserReviewRepository repository;
	
	@Autowired
	private UserReviewMapper mapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<UserReviewDto> findAll() {
		List<UserReview> userReviews = repository.findAll();
		List<UserReviewDto> userReviewDtos = userReviews.stream().map(u -> mapper.getDtoFromEntity(u)).collect(Collectors.toList());
		return userReviewDtos;
	}

	@Override
	@Transactional(readOnly = true)
	public UserReviewDto findOne(String id) {
		UserReview userReview = repository.findOne(id);
		if(userReview == null){
			throw new EntityNotFoundException("User review not found");
		}
		return mapper.getDtoFromEntity(userReview);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserReviewDto> findByUser(String userId) {
		List<UserReview> userReviews = repository.findByUser(userId);
		List<UserReviewDto> userReviewDtos = userReviews.stream().map(u -> mapper.getDtoFromEntity(u)).collect(Collectors.toList());
		return userReviewDtos;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserReviewDto> findByMovie(String movieId) {
		List<UserReview> userReviews = repository.findByMovie(movieId);
		List<UserReviewDto> userReviewDtos = userReviews.stream().map(u -> mapper.getDtoFromEntity(u)).collect(Collectors.toList());
		return userReviewDtos;
	}

	@Override
	@Transactional
	public UserReviewDto create(UserReviewDto userReviewDto) {
		/*UserReview userReview = repository.findOne(userReviewDto.getId());
		if (userReview != null) {
			throw new BadRequestException("Already exists");
		}*/
		UserReview userReview = mapper.getEntityFromDto(userReviewDto, true);
		return mapper.getDtoFromEntity(repository.create(userReview));
	}

	@Override
	@Transactional
	public UserReviewDto update(String id, UserReviewDto userReviewDto) {
		UserReview userReview = repository.findOne(id);
		if (userReview == null) {
			throw new EntityNotFoundException("User review not found");
		}
		userReview = mapper.getEntityFromDto(userReviewDto);
		return mapper.getDtoFromEntity(repository.update(userReview));
	}

	@Override
	@Transactional
	public void delete(String id) {
		UserReview userReview = repository.findOne(id);
		if (userReview == null) {
			throw new EntityNotFoundException("User review not found");
		}
		repository.delete(userReview);
	}
	
	public float getAverageUserRating(String movieId){
		List<UserReviewDto> userReviewDtos = findByMovie(movieId);
		long noOfRates = userReviewDtos.stream().filter(u -> u.getUserRating() != 0).count();
		float totalUserRating = (float) userReviewDtos.stream().mapToDouble(u -> u.getUserRating()).sum();
		return totalUserRating/noOfRates;
	}
}
