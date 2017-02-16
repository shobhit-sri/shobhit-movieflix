package io.egen.api.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.api.entity.Movie;
import io.egen.api.repository.MovieRepository;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Movie> findAll() {
		TypedQuery<Movie> query = entityManager.createNamedQuery("Movie.findAll", Movie.class);
		return query.getResultList();
	}
	
	@Override
	public List<Movie> findByType(String type) {
		TypedQuery<Movie> query = entityManager.createNamedQuery("Movie.findByType", Movie.class);
		query.setParameter("pType", type);
		return query.getResultList();
	}
	
	@Override
	public List<Movie> findByKeyword(String keyword) {
		TypedQuery<Movie> query = entityManager.createNamedQuery("Movie.findByKeyword", Movie.class);
		query.setParameter("pType", "%" + keyword + "%");
		query.setParameter("pTitle", "%" + keyword + "%");
		query.setParameter("pGenre", "%" + keyword + "%");
		query.setParameter("pYear",  "%" + keyword + "%");
		return query.getResultList();
	}

	@Override
	public Movie findOne(String id) {
		return entityManager.find(Movie.class, id);
	}

	@Override
	public Movie findByTitle(String title) {
		TypedQuery<Movie> query = entityManager.createNamedQuery("Movie.findByTitle", Movie.class);
		query.setParameter("pTitle", title);

		List<Movie> movies = query.getResultList();
		if (movies != null && movies.size() == 1) {
			return movies.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Movie findByImdbId(String imdbId) {
		TypedQuery<Movie> query = entityManager.createNamedQuery("Movie.findByImdbId", Movie.class);
		query.setParameter("pImdbId", imdbId);

		List<Movie> movies = query.getResultList();
		if (movies != null && movies.size() == 1) {
			return movies.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Movie create(Movie movie) {
		entityManager.persist(movie);
		return movie;
	}

	@Override
	public Movie update(Movie movie) {
		return entityManager.merge(movie);
	}

	@Override
	public void delete(Movie movie) {
		//TODO: replace below with soft delete after User Story 2 implementation
		entityManager.remove(movie);
	}
}
