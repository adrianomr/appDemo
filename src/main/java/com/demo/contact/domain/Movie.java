package com.demo.contact.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Movie {

	@Id
	@GeneratedValue
	private Long id;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
