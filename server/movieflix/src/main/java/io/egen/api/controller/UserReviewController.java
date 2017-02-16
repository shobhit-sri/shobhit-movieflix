package io.egen.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.api.dto.UserReviewDto;
import io.egen.api.service.UserReviewService;

@RestController
@RequestMapping(value="reviews")
public class UserReviewController {
	@Autowired
	private UserReviewService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<UserReviewDto> findAll() {
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public UserReviewDto findOne(@PathVariable("id") String id) {
		return service.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/movie/{movieId}")
	public List<UserReviewDto> findByMovie(@PathVariable("movieId") String movieId) {
		return service.findByMovie(movieId);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/rating/{movieId}")
	public String getAverageUserRating(@PathVariable("movieId") String movieId) {
		return service.getAverageUserRating(movieId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public UserReviewDto create(@RequestBody UserReviewDto userReviewDto) {
		return service.create(userReviewDto);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public UserReviewDto update(@PathVariable("id") String id, @RequestBody UserReviewDto userReviewDto) {
		return service.update(id, userReviewDto);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}
}
