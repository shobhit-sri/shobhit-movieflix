package io.egen.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.api.dto.MovieDto;
import io.egen.api.service.MovieService;

@RestController
@RequestMapping(value="movies")
public class MovieController {
	
	@Autowired
	private MovieService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<MovieDto> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public MovieDto findOne(@PathVariable("id") String id){
		return service.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/title/{title}")
	public MovieDto findByTitle(@PathVariable("title") String title){
		return service.findByTitle(title);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/type/{type}")
	public List<MovieDto> findByType(@PathVariable("type") String type){
		return service.findByType(type);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/top/{type}")
	public List<MovieDto> findByRating(@PathVariable("type") String type){
		return service.getTopTitlesByImdbRating(type, 0);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/search/{keyword}")
	public List<MovieDto> findByKeyword(@PathVariable("keyword") String keyword){
		return service.findByKeyword(keyword);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/imdbId/{imdbId}")
	public MovieDto findByImdbId(@PathVariable("imdbId") String imdbId){
		return service.findByImdbId(imdbId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public MovieDto create(@RequestBody MovieDto movieDto){
		return service.create(movieDto);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public MovieDto update(@PathVariable("id") String id, @RequestBody MovieDto movieDto){
		return service.update(id, movieDto);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public MovieDto delete(@PathVariable("id") String id){
		return service.delete(id);
	}
}
