package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import reactor.Environment;

@SpringBootApplication
@EnableOAuth2Sso
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker //http://localhost:{PORT}/book/{id}
@EnableZuulProxy //http://localhost:{PORT}/contact-service/contact/
                //http://localhost:{PORT}/profile-service/profiles/
public class IntegrationApplication  extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.logout().and().antMatcher("/**").authorizeRequests()
                .antMatchers("/login","/auth/**").permitAll()
                .anyRequest().authenticated().and().csrf().disable();
    }

    @Bean
    public Environment env(){
        return Environment.initializeIfEmpty();
    }

    public static void main(String[] args) {
        SpringApplication.run(IntegrationApplication.class, args);
    }

}
