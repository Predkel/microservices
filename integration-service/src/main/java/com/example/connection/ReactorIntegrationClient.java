package com.example.connection;

import com.example.model.Contact;
import com.example.model.Dto;
import com.example.model.Profile;
import org.springframework.stereotype.Component;
import reactor.Environment;
import reactor.rx.Stream;
import reactor.rx.Streams;

@Component
public class ReactorIntegrationClient {

    private final Environment environment;

    private final ContactClient contactClient;
    private final ProfileClient profileClient;

    public ReactorIntegrationClient(Environment environment, ContactClient contactClient, ProfileClient profileClient) {
        this.environment = environment;
        this.contactClient = contactClient;
        this.profileClient = profileClient;
    }

    public Stream<Contact> getContacts() {
        return Streams.<Contact>create(subscriber -> {
            this.contactClient.getContacts().forEach(subscriber::onNext);
            subscriber.onComplete();
        }).dispatchOn(this.environment, Environment.cachedDispatcher()).log("contacts");
    }

    public Stream<Profile> getProfiles() {
        return Streams.<Profile>create(subscriber -> {
            this.profileClient.getProfiles().forEach(subscriber::onNext);
            subscriber.onComplete();
        }).dispatchOn(this.environment, Environment.cachedDispatcher()).log("profiles");
    }

    public Stream<Dto> getDto(Stream<Contact> contacts, Stream<Profile> profiles) {
        return Streams.zip(contacts.buffer(), profiles.buffer(),
                tuple -> new Dto(tuple.getT1(), tuple.getT2()));
    }
}
