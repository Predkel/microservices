package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RefreshScope
public class ProjectNameRestController {

    @Value("${configuration.projectName}")
    private String projectName;

    @RequestMapping("/project-name")
    public String projectName() {
        return this.projectName;
    }

}
