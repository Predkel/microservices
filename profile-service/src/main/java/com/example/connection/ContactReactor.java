package com.example.connection;

import com.example.model.Book;
import com.example.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.Environment;
import reactor.rx.Stream;
import reactor.rx.Streams;

import java.util.Collections;

@Component
public class ContactReactor {

    private final Environment environment;

    private final ContactClient contactClient;

    @Autowired
    public ContactReactor(Environment environment, ContactClient contactClient) {
        this.environment = environment;
        this.contactClient = contactClient;
    }

    public Stream<Contact> getContacts() {
        return Streams.<Contact>create(subscriber -> {
            this.contactClient.getContacts().forEach(subscriber::onNext);
            subscriber.onComplete();
        }).dispatchOn(this.environment, Environment.cachedDispatcher()).log("contacts");
    }

    public Stream<Book> getBook(Stream<Contact> contacts) {
        return Streams.zip(Collections.singletonList(contacts.buffer()), tuple -> new Book((Contact) tuple.get(0)));
    }

}