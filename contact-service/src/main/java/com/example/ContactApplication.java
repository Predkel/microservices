package com.example;

import com.example.model.Contact;
import com.example.repository.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
public class ContactApplication {

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
        SpringApplication.run(ContactApplication.class, args);
    }

}
