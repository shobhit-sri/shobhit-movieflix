package io.egen.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import io.egen.api.dto.MovieDto;
import io.egen.api.entity.Movie;

@Component
public class MovieMapper extends ModelMapper {
	public MovieDto getDtoFromEntity(Movie movie){
		return map(movie, MovieDto.class);
	}
	
	public Movie getEntityFromDto(MovieDto movieDto){
		return map(movieDto, Movie.class);
	}
	
	public Movie getEntityFromDto(MovieDto movieDto, boolean isNew){
		Movie movie = getEntityFromDto(movieDto);
		if(isNew){
			movie.setId(new Movie().getId());
			movie.setActive(true);
		}
		return movie;
	}
}
