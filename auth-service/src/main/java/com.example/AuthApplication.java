//package com.example;
//
//import com.example.domain.User;
//import com.example.service.UserService;
//import com.example.service.security.MysqlUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
//import org.springframework.stereotype.Component;
//
//import java.util.stream.Stream;
//
//@SpringBootApplication
//@EnableResourceServer
//@EnableEurekaClient
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class AuthApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(AuthApplication.class, args);
//    }
//
//    @Configuration
//    @EnableWebSecurity
//    protected static class webSecurityConfig extends WebSecurityConfigurerAdapter {
//
//        @Autowired
//        private MysqlUserDetailsService userDetailsService;
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            // @formatter:off
//            http
//                    .authorizeRequests().anyRequest().authenticated()
//                    .and()
//                    .csrf().disable();
//            // @formatter:on
//        }
//
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(userDetailsService)
//                    .passwordEncoder(new BCryptPasswordEncoder());
//        }
//
//        @Override
//        @Bean
//        public AuthenticationManager authenticationManagerBean() throws Exception {
//            return super.authenticationManagerBean();
//        }
//    }
//
//    @Configuration
//    @EnableAuthorizationServer
//    protected static class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
//
//        private TokenStore tokenStore = new InMemoryTokenStore();
//
//        @Autowired
//        @Qualifier("authenticationManagerBean")
//        private AuthenticationManager authenticationManager;
//
//        @Autowired
//        private MysqlUserDetailsService userDetailsService;
//
//        @Autowired
//        private Environment env;
//
//        @Override
//        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
//            // TODO persist clients details
//
//            // @formatter:off
//            clients.inMemory()
//                    .withClient("browser")
//                    .authorizedGrantTypes("refresh_token", "password")
//                    .scopes("ui")
//                    .and()
//                    .withClient("contact-service")
//                    .secret("contact-pass")
//                    .authorizedGrantTypes("client_credentials", "refresh_token")
//                    .scopes("server")
//                    .and()
//                    .withClient("profile-service")
//                    .secret("profile-pass")
//                    .authorizedGrantTypes("client_credentials", "refresh_token")
//                    .scopes("server");
//            // @formatter:on
//        }
//
//        @Override
//        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//            endpoints
//                    .tokenStore(tokenStore)
//                    .authenticationManager(authenticationManager)
//                    .userDetailsService(userDetailsService);
//        }
//
//        @Override
//        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//            oauthServer
//                    .tokenKeyAccess("permitAll()")
//                    .checkTokenAccess("isAuthenticated()");
//        }
//    }
//
//    @Component
//    class SampleDataCLR implements CommandLineRunner {
//
//        private final UserService service;
//
//        @Autowired
//        public SampleDataCLR(UserService service) {
//            this.service = service;
//        }
//
//        @Override
//        public void run(String... args) throws Exception {
//            Stream.of("dima,8827", "igor,1234", "stepa,4321")
//                    .map(x -> x.split(","))
//                    .forEach(tuple -> service.create(new User(tuple[0], tuple[1])));
//        }
//    }
//}
