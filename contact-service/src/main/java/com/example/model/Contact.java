package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contact {

	@Id
	@GeneratedValue
	private Long id;

	private String firstName;

	private String email;

	public Contact() {
	}

	public Contact(String firstName, String email) {
		this.firstName = firstName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmail() {
		return email;
	}

}
