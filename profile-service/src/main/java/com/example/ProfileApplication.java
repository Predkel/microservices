package com.example;

import com.example.model.Profile;
import com.example.repository.ProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@EnableEurekaClient
public class ProfileApplication {

    @Bean
    public CommandLineRunner init(ProfileRepository repository) {
        return args -> {
            repository.deleteAll();
            Arrays.asList(
                    new Profile("Profile1", new Date()),
                    new Profile("Profile2", new Date()),
                    new Profile("Profile2", new Date())
            )
                    .forEach(repository::save);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ProfileApplication.class, args);
    }

}
