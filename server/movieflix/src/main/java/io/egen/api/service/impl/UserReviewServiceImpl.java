package io.egen.api.service.impl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.dto.UserReviewDto;
import io.egen.api.entity.UserReview;
import io.egen.api.exception.EntityNotFoundException;
import io.egen.api.mapper.UserMapper;
import io.egen.api.mapper.UserReviewMapper;
import io.egen.api.repository.UserReviewRepository;
import io.egen.api.service.UserReviewService;
import io.egen.api.service.UserService;

@Service
public class UserReviewServiceImpl implements UserReviewService {

	@Autowired
	private  UserReviewRepository repository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserReviewMapper mapper;
	
	@Autowired
	private UserMapper userMapper;
	
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
		userReviewDto.setUser(userMapper.getEntityFromDto(userService.findByEmail(userReviewDto.getUser().getEmail())));
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
	
	public String getAverageUserRating(String movieId){
		List<UserReviewDto> userReviewDtos = findByMovie(movieId);
		long noOfRates = userReviewDtos.stream().filter(u -> u.getUserRating() != 0).count();
		if(noOfRates < 1){
			return "";
		}
		float totalUserRating = (float) userReviewDtos.stream().mapToDouble(u -> u.getUserRating()).sum();
		float avgRating = totalUserRating/noOfRates;
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(avgRating);
	}
}
