package com.example;

import com.example.connection.ContactReactor;
import com.example.connection.IntegrationClient;
import com.example.model.Book;
import com.example.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.rx.Stream;

@RestController
class BookRestController {

    private final IntegrationClient integrationClient;
    private final ContactReactor contactReactor;

    @Autowired
    public BookRestController(IntegrationClient integrationClient, ContactReactor contactReactor) {
        this.integrationClient = integrationClient;
        this.contactReactor = contactReactor;
    }

    @RequestMapping("/book")
    public Book book() {
        return new Book(integrationClient.getContacts());
    }

    @RequestMapping("/book/react")
    public DeferredResult<Book> passport() {
        DeferredResult<Book> passportDeferredResult = new DeferredResult<>();
        Stream<Contact> contactStream = contactReactor.getContacts();
        this.contactReactor.getBook(contactStream)
                .consume(passportDeferredResult::setResult);
        return passportDeferredResult;
    }

    @RequestMapping("/book/{id}")
    public Book book(@PathVariable Long id) {
        return new Book(integrationClient.getContact(id));
    }
}