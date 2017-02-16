package io.egen.api.repository;

import java.util.List;

import io.egen.api.entity.Movie;

public interface MovieRepository {
	public List<Movie> findAll();
	
	public List<Movie> findByType(String type);
	
	public List<Movie> findByKeyword(String keyword);
	
	public Movie findOne(String id);
	
	public Movie findByTitle(String title);
	
	public Movie findByImdbId(String imdbId);
	
	public Movie create(Movie movie);
	
	public Movie update(Movie movie);
	
	public void delete(Movie movie);
}
