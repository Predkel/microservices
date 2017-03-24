package com.example;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@FeignClient("contact-service")
interface ContactClient {

    @RequestMapping(method = RequestMethod.GET, value = "/contacts")
    Collection<Contact> getContacts();

    @RequestMapping(method = RequestMethod.GET, value = "/contacts/{id}")
    Contact getContact(@PathVariable("id") Long id);

}
