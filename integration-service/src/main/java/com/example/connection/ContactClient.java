package com.example.connection;

import com.example.model.Contact;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@FeignClient("contact-service")
public interface ContactClient {

    @RequestMapping(method = RequestMethod.GET, value = "/contact")
    Collection<Contact> getContacts();

    @RequestMapping(method = RequestMethod.GET, value = "/contact/{id}")
    Contact getContact(@PathVariable("id") Long id);

}
