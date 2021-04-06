package com.demo.contact.web;

import com.demo.contact.domain.Contact;
import com.demo.contact.domain.MovieScore;
import com.demo.contact.service.ContactService;
import com.demo.contact.util.ContactUtils;
import com.demo.contact.web.resource.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public Iterable<Contact> listContacts() {
        return contactService.listContacts();
    }

    @GetMapping("email/{email}")
    public Iterable<Contact> listContactsByEmail(@PathVariable("email") String email) {
        System.out.println("In listContactsByEmail");
        System.out.println("email : " + email);
        return contactService.listContactsByEmail(email);
    }

    @GetMapping("phoneNumber/{phoneNumber}")
    public Iterable<Contact> listContactsByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {

        return contactService.listContactsByPhoneNumber(phoneNumber);
    }

    @GetMapping("address/city/{city}")
    public Iterable<Contact> listContactsByCity(@PathVariable("city") String city) {
        System.out.println("In listContactsByCity");
        System.out.println("city : " + city);
        return contactService.listContactsByCity(city);
    }

    @GetMapping("address/state/{state}")
    public Iterable<Contact> listContactsByState(@PathVariable("state") String state) {
        System.out.println("In listContactsByState");
        System.out.println("state : " + state);
        return contactService.listContactsByState(state);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Contact addContact(@RequestBody Contact contact) {
        contact = contactService.addContact(contact);
        return contact;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateContact(@RequestBody Contact contact) {
        if (contactService.updateContact(contact)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{contactId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteContact(@PathVariable("contactId") Long contactId) {
        if (contactService.deleteContact(contactId)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{contactId}")
    public Contact getContactById(@PathVariable("contactId") Long contactId) {
        return contactService.getContactById(contactId);
    }

    @GetMapping("{contactId}/movie")
    public List<MovieScore> findMoviesByContactId(@PathVariable("contactId") Long contactId) {
        return contactService.findMovies(contactId);
    }

    @PostMapping("{contactId}/movie/{movieId}")
    public MovieScore addContact(@PathVariable("contactId") Long contactId,
                                 @PathVariable("movieId") Long movieId,
                                 @RequestBody @Valid MovieRequest movieRequest
                                 ) {
        return contactService.saveMovieScore(contactId, movieId, movieRequest);
    }

    @ResponseBody
    @RequestMapping(value = "profilePicture/{contactId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getProfilePicture(@PathVariable("contactId") Long contactId) {
        return ResponseEntity.ok().body(new InputStreamResource(ContactUtils.getProfilePicture(contactId)));
    }
}
