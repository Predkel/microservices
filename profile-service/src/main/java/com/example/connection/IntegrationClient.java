package com.example.connection;

import com.example.model.Contact;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class IntegrationClient {

    private final ContactClient contactClient;

    public IntegrationClient(ContactClient contactClient) {
        this.contactClient = contactClient;
    }

    @HystrixCommand(fallbackMethod = "getContactFallback")
    public Contact getContact(Long id) {
        return this.contactClient.getContact(id);
    }

    public Contact getContactFallback(Long id) {
        System.out.println("getContactFallback");
        return new Contact();
    }

    @HystrixCommand(fallbackMethod = "getContactsFallback")
    public Collection<Contact> getContacts() {
        return this.contactClient.getContacts();
    }

    public Collection<Contact> getContactsFallback() {
        System.out.println("getContactsFallback");
        return Collections.emptyList();
    }
}
