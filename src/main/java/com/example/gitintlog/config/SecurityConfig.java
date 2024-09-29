package com.example.gitintlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Authorize requests
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/login", "/oauth2/**").permitAll() // Allow access to these pages
                                .anyRequest().authenticated() // Require authentication for all other requests
                )
                // Enable OAuth2 login
                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .loginPage("/login") // Specify the login page
                                .defaultSuccessUrl("/dashboard", true) // Redirect to /dashboard after successful login
                                .failureUrl("/login?error=true") // Redirect to login page on failure
                )
                // Enable logout
                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/") // Redirect to homepage after logout
                                .permitAll()
                );

        return http.build();
    }
}
