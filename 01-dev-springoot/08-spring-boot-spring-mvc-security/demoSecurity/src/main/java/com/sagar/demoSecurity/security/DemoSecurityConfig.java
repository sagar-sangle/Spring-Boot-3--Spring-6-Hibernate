package com.sagar.demoSecurity.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {



    //this code provides basic authentication at line 23
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
//        http.csrf().disable()
//                .authorizeHttpRequests((authrize)->{
//                    authrize.anyRequest().authenticated();
//                }).httpBasic(Customizer.withDefaults());
//        return http.build();
//    }


    //this code provides defualt form based authentication at line 34
    @Bean
    public static PasswordEncoder pass()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests((authrize)->{
                    authrize.anyRequest().authenticated();
                }).formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    InMemoryUserDetailsManager userDetailsManager (){
        //since we are using user here
        //springboot will not take users from prooperties file
      UserDetails sagar = User.builder()
              .username("sagar")
              .password("{noop}sagar123")
              .roles("EMPLOYEE")
              .build();

      UserDetails admin = User.builder()
              .username("admin")
              .password("admin123")
              .roles("ADMIN,EMPLOYEE")
              .build();

//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test1231")
//                .roles("EMPLOYEE","MANAGER")
//                .build();
//
//
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test1231")
//                .roles("EMPLOYEE","MANAGER","ADMIN")
//                .build();



        //spring boot will use users from inMemoryDetailsManager
        return new InMemoryUserDetailsManager(sagar,admin);
    }

}
