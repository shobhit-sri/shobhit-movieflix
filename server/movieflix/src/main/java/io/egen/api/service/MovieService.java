package io.egen.api.service;

import java.util.List;

import io.egen.api.dto.MovieDto;

public interface MovieService {
	public List<MovieDto> findAll();
	
	public MovieDto findOne(String id);
	
	public MovieDto findByTitle(String title);
	
	public MovieDto findByImdbId(String imdbId);
	
	public MovieDto create(MovieDto movieDto);
	
	public MovieDto update(String id, MovieDto movieDto);
	
	public void delete(String id);
}
