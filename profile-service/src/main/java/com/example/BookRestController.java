package com.example;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BookRestController {

    private final IntegrationClient integrationClient;

    public BookRestController(IntegrationClient integrationClient) {
        this.integrationClient = integrationClient;
    }

    @RequestMapping("/book")
    public Book book() {
        return new Book(integrationClient.getContacts());
    }

    @RequestMapping("/book/{id}")
    public Book book(@PathVariable Long id) {
        return new Book(integrationClient.getContact(id));
    }
}