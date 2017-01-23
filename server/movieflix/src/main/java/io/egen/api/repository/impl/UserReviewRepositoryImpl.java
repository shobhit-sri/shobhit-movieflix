package io.egen.api.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.api.entity.UserReview;
import io.egen.api.repository.UserReviewRepository;

@Repository
public class UserReviewRepositoryImpl implements UserReviewRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<UserReview> findAll() {
		TypedQuery<UserReview> query = entityManager.createNamedQuery("UserReview.findAll", UserReview.class);
		return query.getResultList();
	}

	@Override
	public UserReview findOne(String id) {
		return entityManager.find(UserReview.class, id);
	}

	@Override
	public List<UserReview> findByUser(String userId) {
		TypedQuery<UserReview> query = entityManager.createNamedQuery("UserReview.findByUser", UserReview.class);
		query.setParameter("pUserId", userId);
		return query.getResultList();
	}

	@Override
	public List<UserReview> findByMovie(String movieId) {
		TypedQuery<UserReview> query = entityManager.createNamedQuery("UserReview.findByMovie", UserReview.class);
		query.setParameter("pMovieId", movieId);
		return query.getResultList();
	}

	@Override
	public UserReview create(UserReview userReview) {
		entityManager.persist(userReview);
		return userReview;
	}

	@Override
	public UserReview update(UserReview userReview) {
		entityManager.merge(userReview);
		return userReview;
	}

	@Override
	public void delete(UserReview userReview) {
		entityManager.remove(userReview);
	}

}
