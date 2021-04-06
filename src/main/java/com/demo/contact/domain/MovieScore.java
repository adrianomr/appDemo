package com.demo.contact.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class MovieScore {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name="contact_id", nullable=false)
	private Contact contact;
	@ManyToOne
	@JoinColumn(name="movie_id", nullable=false)
	private Movie movie;
	private Integer score;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
