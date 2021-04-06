package com.demo.contact.repository;

import com.demo.contact.domain.Contact;
import com.demo.contact.domain.MovieScore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieScoreRepository extends CrudRepository<MovieScore, Long> {

    List<MovieScore> findAllByContactContactId(Long contactId);
}
