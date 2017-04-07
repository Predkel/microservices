package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import reactor.Environment;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker //http://localhost:{PORT}/book/{id}
@EnableZuulProxy //http://localhost:{PORT}/contact-service/contact/
                //http://localhost:{PORT}/profile-service/profiles/
public class IntegrationApplication {

    @Bean
    public Environment env(){
        return Environment.initializeIfEmpty();
    }

    public static void main(String[] args) {
        SpringApplication.run(IntegrationApplication.class, args);
    }

}
