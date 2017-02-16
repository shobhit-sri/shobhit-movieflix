package io.egen.api.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.egen.api.dto.MovieDto;
import io.egen.api.entity.Movie;
import io.egen.api.exception.BadRequestException;
import io.egen.api.exception.EntityNotFoundException;
import io.egen.api.mapper.MovieMapper;
import io.egen.api.repository.MovieRepository;
import io.egen.api.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private MovieMapper mapper;
	
	@Override
	@Transactional(readOnly = true)
	public List<MovieDto> findAll() {
		List<Movie> movies = repository.findAll();
		List<MovieDto> movieDtos = movies.stream().map(m -> mapper.getDtoFromEntity(m)).collect(Collectors.toList());
		return movieDtos;
	}

	@Override
	@Transactional(readOnly = true)
	public MovieDto findOne(String id) {
		Movie movie = repository.findOne(id);
		if(movie == null){
			throw new EntityNotFoundException("Movie not found");
		}
		return mapper.getDtoFromEntity(movie);
	}

	@Override
	@Transactional(readOnly = true)
	public MovieDto findByTitle(String title) {
		Movie movie = repository.findByTitle(title);
		if(movie == null){
			throw new EntityNotFoundException("Movie with this title is not found");
		}
		return mapper.getDtoFromEntity(movie);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MovieDto> findByType(String type){
		List<Movie> movies = repository.findByType(type);
		List<MovieDto> movieDtos = movies.stream().map(m -> mapper.getDtoFromEntity(m)).collect(Collectors.toList());
		return movieDtos;
	}

	@Override
	@Transactional(readOnly = true)
	public MovieDto findByImdbId(String imdbId) {
		Movie movie = repository.findByImdbId(imdbId);
		if(movie == null){
			throw new EntityNotFoundException("Movie not found");
		}
		return mapper.getDtoFromEntity(movie);
	}

	@Override
	@Transactional
	public MovieDto create(MovieDto movieDto) {
		Movie movie = repository.findByImdbId(movieDto.getImdbId());
		if (movie != null) {
			throw new BadRequestException("Movie already exists");
		}
		movie = mapper.getEntityFromDto(movieDto, true);
		return mapper.getDtoFromEntity(repository.create(movie));
	}

	@Override
	@Transactional
	public MovieDto update(String id, MovieDto movieDto) {
		Movie movie = repository.findOne(id);
		if (movie == null) {
			throw new EntityNotFoundException("Movie not found");
		}
		movie = mapper.getEntityFromDto(movieDto);
		return mapper.getDtoFromEntity(repository.update(movie));
	}

	@Override
	@Transactional
	public MovieDto delete(String id) {
		Movie movie = repository.findOne(id);
		if (movie == null) {
			throw new EntityNotFoundException("Movie not found");
		}
		//TODO: Set delete_by to current user after User Story 2 (sign in functionality) is done
		movie.setActive(false);
		return mapper.getDtoFromEntity(repository.update(movie));
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MovieDto> getTopTitlesByImdbRating(String type, int topN){
		Comparator<MovieDto> compareByImdbRating = (m1,m2) -> 
												  m1.getImdbRating() < m2.getImdbRating() ? 1
												: m1.getImdbRating() > m2.getImdbRating() ? -1
												: 0;
												
		Stream<MovieDto> movieDtoStream = findByType(type).stream().sorted(compareByImdbRating);
		
		List<MovieDto> movieDtos = topN>0 ? movieDtoStream.limit(topN).collect(Collectors.toList())
										  : movieDtoStream.collect(Collectors.toList());
		movieDtoStream.close();
		return movieDtos;
	}

	@Override
	@Transactional(readOnly = true)
	public List<MovieDto> findByKeyword(String keyword) {
		List<Movie> movies = repository.findByKeyword(keyword);
		List<MovieDto> movieDtos = movies.stream().map(m -> mapper.getDtoFromEntity(m)).collect(Collectors.toList());
		return movieDtos;
	}

}
