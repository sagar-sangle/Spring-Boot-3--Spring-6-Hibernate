package com.CodeWithMe.springmvcsecurity.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager detailsManager(){

        UserDetails sagar = User.builder()
                .username("sagar")
                .password("{noop}test123")
                .roles("EMPLOYEE","ADMIN")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();

        return  new InMemoryUserDetailsManager(sagar,susan);

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(config->
                config
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        );


        http.formLogin(form->
                form
                        .loginPage("/mylogin")
                        .loginProcessingUrl("/authenticate")
                        .permitAll()

        ).
                logout(logout->
                        logout.permitAll()
                )
                .exceptionHandling(exception->
                        exception.
                                accessDeniedPage("/access-denied")
                );
        return http.build();

    }
}
