package io.egen.api.service;

import java.util.List;

import io.egen.api.dto.MovieDto;

public interface MovieService {
	public List<MovieDto> findAll();
	
	public MovieDto findOne(String id);
	
	public MovieDto findByTitle(String title);
	
	public List<MovieDto> findByType(String type);
	
	public MovieDto findByImdbId(String imdbId);
	
	public MovieDto create(MovieDto movieDto);
	
	public MovieDto update(String id, MovieDto movieDto);
	
	public MovieDto delete(String id);
	
	public List<MovieDto> getTopTitlesByImdbRating(String type, int topN);
	
	public List<MovieDto> findByKeyword(String keyword);
}
