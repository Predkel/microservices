package com.example.ex.controller;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Because this application is also a User Info Resource Server, we expose info about the logged in user at:
 *
 *     http://localhost:9090/auth/user
 */
@RestController
public class ResourceController {

    /**
     * We expose the minimal required data:
     * - The user name of the logged in user
     * - The granted authorities (roles)
     *
     * It is possible to add more (custom) data here about the logged in user, if needed.
     */
    @RequestMapping(value = { "/user" }, produces = "application/json")
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("user", user.getUserAuthentication().getPrincipal());
        userDetails.put("authorities",
                AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        return userDetails;
    }
}
