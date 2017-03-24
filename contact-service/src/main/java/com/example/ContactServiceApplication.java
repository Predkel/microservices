package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
public class ContactServiceApplication {

    @Autowired
    void setEnvironment(Environment e) {
        System.out.println(e.getProperty("configuration.projectName"));
    }

    @Bean
    public CommandLineRunner init(ContactRepository contactRepository) {
        return args -> {
            contactRepository.deleteAll();
            Arrays.asList(
                    new Contact("Dima", "dima@gmail.com"),
                    new Contact("Igor", "igor@gmail.com"),
                    new Contact("Stepa", "stepa@gmail.com")
            )
                    .forEach(contactRepository::save);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ContactServiceApplication.class, args);
    }

}
