package com.demo.contact.service;

import com.demo.contact.domain.Contact;
import com.demo.contact.domain.Movie;
import com.demo.contact.exception.ResourceNotFoundException;
import com.demo.contact.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public Movie findMovieById(Long id) {
        return movieRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie"));
    }
}
