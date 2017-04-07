package com.example.connection;

import com.example.model.Contact;
import com.example.model.Profile;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@FeignClient("profile-service")
public interface ProfileClient {

    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    Collection<Profile> getProfiles();

    @RequestMapping(method = RequestMethod.GET, value = "/profile/{id}")
    Contact getProfile(@PathVariable("id") Long id);

}