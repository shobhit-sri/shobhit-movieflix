package io.egen.api.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;

@Entity
@Table(name="USER_REVIEW")
@NamedQueries({ 
	@NamedQuery(name = "UserReview.findAll", query = "SELECT u FROM UserReview u"),
	@NamedQuery(name = "UserReview.findByUser", query = "SELECT u FROM UserReview u WHERE u.user.id=:pUserId"),
	@NamedQuery(name = "UserReview.findByMovie", query = "SELECT u FROM UserReview u WHERE u.movie.id=:pMovieId") 
})
public class UserReview {
	@Id
	private String id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Movie movie;
	
	@Max(5)
	@Column(name="USER_RATING")
	private float userRating;
	
	@Column(name="USER_COMMENT")
	private String userComment;
	
	public UserReview() {
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public float getUserRating() {
		return userRating;
	}
	public void setUserRating(float userRating) {
		this.userRating = userRating;
	}
	public String getUserComment() {
		return userComment;
	}
	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}
}
