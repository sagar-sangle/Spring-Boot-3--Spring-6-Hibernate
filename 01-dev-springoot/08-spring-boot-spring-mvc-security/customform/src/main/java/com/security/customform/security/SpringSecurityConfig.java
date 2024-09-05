package com.security.customform.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SpringSecurityConfig {


//    @Bean
//    InMemoryUserDetailsManager userDetailsManager()
//    {
//        UserDetails sagar = User.builder()
//                .username("sagar")
//                .password("{noop}sagar123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}john123")
//                .roles("EMPLOYEE","ADMIN")
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}mary123")
//                .roles("EMPLOYEE","MANAGER","ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(sagar,john,mary);
//    }
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource)
    {
        return new JdbcUserDetailsManager(dataSource);
    }




    //configure security of web paths in application login logout etc...
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception
    {
        http.authorizeHttpRequests(configurer ->
                        configurer//any req must be authenticated (logged in)
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                //defualt login page but must create controller for this mapping which will return login page
                                .loginPage("/showMyLoginPage")


                                //login form should post data to this url for processing(checks id and pass)
                                //no controller required for this springboot automatically provide us all the backend handling
                                //for checking user and pass

                                .loginProcessingUrl("/authenticateTheUser")

                                //for default success after logging in
//                                .defaultSuccessUrl("/welcome")

                                //allow everyone to see login page without logged in
                                .permitAll()
                        )
                    .logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer
                                .accessDeniedPage("/access-denied")
                );
        return http.build();
    }

}

