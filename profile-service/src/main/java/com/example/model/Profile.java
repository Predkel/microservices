package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Profile {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Date date;

    public Profile() {
    }

    public Profile(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (!getName().equals(profile.getName())) return false;
        return getDate().equals(profile.getDate());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getDate().hashCode();
        return result;
    }
}
