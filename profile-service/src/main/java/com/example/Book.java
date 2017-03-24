package com.example;

import java.util.Collection;
import java.util.Collections;

public class Book {
    Collection<Contact> contacts;

    public Book() {
    }

    public Book(Collection<Contact> contacts) {
        this.contacts = contacts;
    }

    public Book(Contact contact) {
        this.contacts = Collections.singletonList(contact);
    }

    public Collection<Contact> getContacts() {
        return contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return getContacts() != null ? getContacts().equals(book.getContacts()) : book.getContacts() == null;
    }

    @Override
    public int hashCode() {
        return getContacts() != null ? getContacts().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Book{" +
                "contacts=" + contacts +
                '}';
    }
}
