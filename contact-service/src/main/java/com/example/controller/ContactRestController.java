package com.example.controller;

import com.example.model.Contact;
import com.example.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactRestController {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactRestController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> getContacts() {
        return this.contactRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Contact getContact(@PathVariable Long id) {
        return this.contactRepository.findOne(id);
    }

//    @RequestMapping(value = "/{firstName}", method = RequestMethod.GET)
//    public Contact getContact(@PathVariable String firstName) {
//        return this.contactRepository.findByFirstName(firstName);
//    }

    @RequestMapping(method = RequestMethod.POST)
    public Contact createContact(@RequestBody Contact contact) {
        return this.contactRepository.save(new Contact(contact.getFirstName(), contact.getEmail()));
    }

}
