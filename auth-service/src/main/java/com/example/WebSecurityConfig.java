//package com.example;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
//import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.Filter;
//
//@Configuration
////@Order(-20)
//@EnableOAuth2Client
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private OAuth2ClientContext oauth2ClientContext;
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .formLogin().loginPage("/login").permitAll()
//                .and().httpBasic()
//                .and()
//                .requestMatchers()
//                //specify urls handled
//                .antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
//                .antMatchers("/fonts/**", "/js/**", "/css/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/login/**", "/fonts/**", "/js/**", "/css/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(googleSsoFilter(), BasicAuthenticationFilter.class);
//
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("reader")
//                .password("reader")
//                .authorities("ROLE_READER")
//                .and()
//                .withUser("writer")
//                .password("writer")
//                .authorities("ROLE_READER", "ROLE_WRITER")
//                .and()
//                .withUser("guest")
//                .password("guest")
//                .authorities("ROLE_GUEST");
//    }
//
//    @Override
//    @Bean(name = "userDetailsService")
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return super.userDetailsServiceBean();
//    }
//
//    /**
//     * Create a Google OAuth2 SSO provider at 'http://localhost:8889/auth/login/google'.
//     */
//    private Filter googleSsoFilter() {
//        OAuth2ClientAuthenticationProcessingFilter ssoFilter =
//                new OAuth2ClientAuthenticationProcessingFilter("/login/google");
//
//        ssoFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
//
//        OAuth2RestTemplate ssoTemplate = new OAuth2RestTemplate(google().getClient(), oauth2ClientContext);
//        ssoFilter.setRestTemplate(ssoTemplate);
//
//        UserInfoTokenServices tokenServices = new UserInfoTokenServices(
//                google().getResource().getUserInfoUri(), google().getClient().getClientId());
//        tokenServices.setAuthoritiesExtractor(googleAuthoritiesExtractor());
//        ssoFilter.setTokenServices(tokenServices);
//
//        return ssoFilter;
//    }
//
//    /**
//     * Assign a special role to users logging in through Google. If no AuthoritiesExtractor is provided, the
//     * default implementation will be used:
//     * <p>
//     * {@link org.springframework.boot.autoconfigure.security.oauth2.resource.FixedAuthoritiesExtractor}
//     */
//    private AuthoritiesExtractor googleAuthoritiesExtractor() {
//        return map -> AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_EXTERNAL_USER");
//    }
//
//    @Bean
//    @ConfigurationProperties("google")
//    protected ClientResources google() {
//        return new ClientResources();
//    }
//
//    /**
//     * The order in which the filters execute are very important. OAuth2ClientContextFilter must be invoked before
//     * OAuth2AuthenticationProcessingFilter. Because when a redirect to Google is required,
//     * OAuth2AuthenticationProcessingFilter throws a UserRedirectException which the oauth2ClientContextFilter handles
//     * and generates a redirect request to Google. Subsequently the response from Google is handled by the
//     * OAuth2AuthenticationProcessingFilter to populate the Authentication object and stored in the SecurityContext.
//     * <p>
//     * The OAuth2ClientContextFilter is not part of the Spring Security Filter Chain, but is a separate Servlet Filter.
//     * Therefore we order the filter just before the Spring Security Filter Chain (getFilterOrder() - 1).
//     */
//    @Bean
//    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter, SecurityProperties securityProperties) {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(filter);
//        registration.setOrder(securityProperties.getFilterOrder() - 1);
//        return registration;
//    }
//}