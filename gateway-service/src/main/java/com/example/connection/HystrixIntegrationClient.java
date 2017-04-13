package com.example.connection;

import com.example.model.Contact;
import com.example.model.Profile;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class HystrixIntegrationClient {

    private final ContactClient contactClient;
    private final ProfileClient profileClient;

    public HystrixIntegrationClient(ContactClient contactClient, ProfileClient profileClient) {
        this.contactClient = contactClient;
        this.profileClient = profileClient;
    }

    @HystrixCommand(fallbackMethod = "getProfilesFallback")
    public Collection<Profile> getProfiles() {
        return this.profileClient.getProfiles();
    }
    private Collection<Profile> getProfilesFallback() {
        System.out.println("getProfilesFallback");
        return Collections.emptyList();
    }

    @HystrixCommand(fallbackMethod = "getContactsFallback")
    public Collection<Contact> getContacts() {
        return this.contactClient.getContacts();
    }
    private Collection<Contact> getContactsFallback() {
        System.out.println("getContactsFallback");
        return Collections.emptyList();
    }
}
