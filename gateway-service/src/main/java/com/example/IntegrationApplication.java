package com.example;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import reactor.Environment;

@SpringBootApplication
@EnableOAuth2Sso
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker //http://localhost:{PORT}/book/{id}
@EnableZuulProxy //http://localhost:{PORT}/contact-service/contact/
                //http://localhost:{PORT}/profile-service/profiles/

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class IntegrationApplication  extends WebSecurityConfigurerAdapter {

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
            requestTemplate.header("Authorization", "Bearer " + details.getTokenValue());
        };
    }

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
