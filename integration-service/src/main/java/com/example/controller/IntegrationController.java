package com.example.controller;

import com.example.connection.ContactClient;
import com.example.connection.HystrixIntegrationClient;
import com.example.connection.ProfileClient;
import com.example.connection.ReactorIntegrationClient;
import com.example.model.Contact;
import com.example.model.Dto;
import com.example.model.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.rx.Stream;

import java.util.Collection;

@RestController
class IntegrationController {

    private final ContactClient contactClient;
    private final ProfileClient profileClient;
    private final HystrixIntegrationClient hystrix;
    private final ReactorIntegrationClient reactor;

    IntegrationController(ContactClient contactClient, ProfileClient profileClient, HystrixIntegrationClient hystrix, ReactorIntegrationClient reactor) {
        this.contactClient = contactClient;
        this.profileClient = profileClient;
        this.hystrix = hystrix;
        this.reactor = reactor;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public Collection<Contact> contacts() {
        return contactClient.getContacts();
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public Collection<Profile> profiles() {
        return profileClient.getProfiles();
    }


    @RequestMapping(value = "/hystrix/contact", method = RequestMethod.GET)
    public Collection<Contact> hystrixContacts() {
        return hystrix.getContacts();
    }

    @RequestMapping(value = "/hystrix/profile", method = RequestMethod.GET)
    public Collection<Profile> hystrixProfiles() {
        return hystrix.getProfiles();
    }

    @RequestMapping("/reactor/dto")
    public DeferredResult<Dto> reactorDto() {
        DeferredResult<Dto> passportDeferredResult = new DeferredResult<>();
        Stream<Contact> contacts = this.reactor.getContacts();
        Stream<Profile> profiles = this.reactor.getProfiles();

        this.reactor.getDto(contacts, profiles)
                .consume(passportDeferredResult::setResult);

        return passportDeferredResult;
    }
}