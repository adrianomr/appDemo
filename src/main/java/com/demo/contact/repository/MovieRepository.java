package com.demo.contact.repository;

import com.demo.contact.domain.Contact;
import com.demo.contact.domain.Movie;
import com.demo.contact.domain.MovieScore;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    Optional<Movie> findById(Long id);

}
