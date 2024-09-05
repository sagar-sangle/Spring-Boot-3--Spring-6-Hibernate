package com.CodeWithMe.springmvcsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
/*
When using Spring Boot, @EnableWebSecurity is not required. This feature is automatically configured by Spring Boot when it detects the spring-boot-starter-security dependency in the pom.xml file.

It is possible that you read a non-Spring Boot example on the internet ... or just an older/legacy blog post.
 */
public class MVCSecurityConfig {

    /*
    In Spring Security, the InMemoryUserDetailsManager is a simple implementation of the UserDetailsService interface that stores user details in memory. It is commonly used for quick setups and testing scenarios. However, it's important to note that it is not suitable for production use, as it does not persist user details across application restarts.

Remember that when using InMemoryUserDetailsManager, user details are stored only for the lifetime of the application. Once the application is restarted, the user details will be lost, and users need to be redefined in the code. For production scenarios, it is recommended to use a persistent data store, such as a database, to store user details securely.
     */

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")//{id}password
                .roles("EMPLOYEE")
                .build();
        UserDetails sagar = User.builder()
                .username("sagar")
                .password("{noop}test123")//{id}password
                .roles("EMPLOYEE","ADMIN")
                .build();
        UserDetails Susan = User.builder()
                .username("susan")
                .password("{noop}test123")//{id}password
                .roles("MANAGER","EMPLOYEE","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john,sagar,Susan);

    }

    @Bean
  public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{

      http.authorizeHttpRequests(configurer->
                configurer.
                        anyRequest().authenticated()
              )
              .formLogin(form->
                      form
                              .loginPage("/mylogin")
                              .loginProcessingUrl("/authenticate")
                              .permitAll()
              )
              .logout(logout->
                      logout.permitAll()
              );

      return http.build();

    }


}
