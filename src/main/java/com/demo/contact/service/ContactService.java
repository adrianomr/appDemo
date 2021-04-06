package com.demo.contact.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.demo.contact.domain.Contact;
import com.demo.contact.domain.Movie;
import com.demo.contact.domain.MovieScore;
import com.demo.contact.exception.ResourceNotFoundException;
import com.demo.contact.repository.MovieScoreRepository;
import com.demo.contact.util.ContactUtils;
import com.demo.contact.web.ContactController;
import com.demo.contact.web.resource.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.contact.repository.ContactRepository;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private MovieScoreRepository movieScoreRepository;
    @Autowired
    private MovieService movieService;

    public Iterable<Contact> listContacts() {
        Iterable<Contact> list = contactRepository.findAll();
        for (Contact contact : list) {
            contact.add(linkTo(ContactController.class).slash(contact.getContactId()).withSelfRel());
            contact.add(
                    linkTo(methodOn(ContactController.class).deleteContact(contact.getContactId())).withRel("delete"));
            contact.add(linkTo(methodOn(ContactController.class).getProfilePicture(contact.getContactId()))
                    .withRel("view"));
        }
        return list;
    }

    public Contact addContact(Contact contact) {
        String encodedImage = contact.getProfileImage();
        contact.setProfileImage(null);
        contact.setContactId(null);
        contact.getAddress().setAddressId(null);
        contact = contactRepository.save(contact);
        if (contact != null) {
            ContactUtils.saveImageToServer(ContactUtils.decodeToImage(encodedImage),
                    String.valueOf(contact.getContactId()));
        }
        return contact;
    }

    public boolean deleteContact(Long contactId) {
        try {
            contactRepository.delete(contactId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Contact getContactById(Long contactId) {
        Contact contact = contactRepository
                .findByContactId(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact"));
        contact.add(
                linkTo(methodOn(ContactController.class).getProfilePicture(contact.getContactId())).withRel("view"));
        contact.add(linkTo(methodOn(ContactController.class).updateContact(null)).withRel("save"));
        return contact;
    }

    public boolean updateContact(Contact contact) {
        String encodedImage = contact.getProfileImage();
        contact.setProfileImage(null);
        contact = contactRepository.save(contact);
        if (contact != null) {
            ContactUtils.saveImageToServer(ContactUtils.decodeToImage(encodedImage),
                    String.valueOf(contact.getContactId()));
        }
        return contact != null;
    }

    public Iterable<Contact> listContactsByEmail(String email) {
        Iterable<Contact> list = contactRepository.listContactsByEmail("%" + email.trim().replace(" ", "%") + "%");
        for (Contact contact : list) {
            contact.add(linkTo(ContactController.class).slash(contact.getContactId()).withSelfRel());
            contact.add(
                    linkTo(methodOn(ContactController.class).deleteContact(contact.getContactId())).withRel("delete"));
            contact.add(linkTo(methodOn(ContactController.class).getProfilePicture(contact.getContactId()))
                    .withRel("view"));
        }
        return list;
    }

    public Iterable<Contact> listContactsByPhoneNumber(String phoneNumber) {
        Iterable<Contact> list = contactRepository
                .listContactsByPhoneNumber("%" + phoneNumber.trim().replace(" ", "%") + "%");
        for (Contact contact : list) {
            contact.add(linkTo(ContactController.class).slash(contact.getContactId()).withSelfRel());
            contact.add(
                    linkTo(methodOn(ContactController.class).deleteContact(contact.getContactId())).withRel("delete"));
            contact.add(linkTo(methodOn(ContactController.class).getProfilePicture(contact.getContactId()))
                    .withRel("view"));
        }
        return list;
    }

    public Iterable<Contact> listContactsByCity(String city) {
        Iterable<Contact> list = contactRepository.listContactsByCity("%" + city.trim().replace(" ", "%") + "%");
        for (Contact contact : list) {
            contact.add(linkTo(ContactController.class).slash(contact.getContactId()).withSelfRel());
            contact.add(
                    linkTo(methodOn(ContactController.class).deleteContact(contact.getContactId())).withRel("delete"));
            contact.add(linkTo(methodOn(ContactController.class).getProfilePicture(contact.getContactId()))
                    .withRel("view"));
        }
        return list;
    }

    public Iterable<Contact> listContactsByState(String state) {
        Iterable<Contact> list = contactRepository.listContactsByState("%" + state.trim().replace(" ", "%") + "%");
        for (Contact contact : list) {
            contact.add(linkTo(ContactController.class).slash(contact.getContactId()).withSelfRel());
            contact.add(
                    linkTo(methodOn(ContactController.class).deleteContact(contact.getContactId())).withRel("delete"));
            contact.add(linkTo(methodOn(ContactController.class).getProfilePicture(contact.getContactId()))
                    .withRel("view"));
        }
        return list;
    }

    public MovieScore saveMovieScore(MovieScore movieScore) {
        return movieScoreRepository.save(movieScore);
    }

    public MovieScore saveMovieScore(Long contactId, Long movieId, MovieRequest movieRequest) {
        MovieScore movieScore = new MovieScore();
        Contact contact = getContactById(contactId);
        Movie movie = movieService.findMovieById(movieId);
        movieScore.setScore(movieRequest.getScore());
        movieScore.setContact(contact);
        movieScore.setMovie(movie);
        return movieScoreRepository.save(movieScore);
    }

    public List<MovieScore> findMovies(Long contactId) {
        return movieScoreRepository.findAllByContactContactId(contactId);
    }
}
