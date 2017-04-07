package com.example.model;

import java.util.Collection;

public class Dto {

    private Collection<Contact> contacts;
    private Collection<Profile> profiles;

    public Dto() {
    }

    public Dto(Collection<Contact> contacts, Collection<Profile> profiles) {
        this.contacts = contacts;
        this.profiles = profiles;
    }

    public Collection<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Collection<Contact> contacts) {
        this.contacts = contacts;
    }

    public Collection<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Collection<Profile> profiles) {
        this.profiles = profiles;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dto dto = (Dto) o;

        if (getContacts() != null ? !getContacts().equals(dto.getContacts()) : dto.getContacts() != null) return false;
        return getProfiles() != null ? getProfiles().equals(dto.getProfiles()) : dto.getProfiles() == null;
    }

    @Override
    public int hashCode() {
        int result = getContacts() != null ? getContacts().hashCode() : 0;
        result = 31 * result + (getProfiles() != null ? getProfiles().hashCode() : 0);
        return result;
    }
}
