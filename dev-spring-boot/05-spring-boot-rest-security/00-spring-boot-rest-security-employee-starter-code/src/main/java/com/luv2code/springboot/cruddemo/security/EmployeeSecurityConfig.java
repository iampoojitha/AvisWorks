package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class EmployeeSecurityConfig {

    // add support for JDBC, No more Hard coded anymore...
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    // This bean is intended to provide the access to api endpoints action to the user based on the roles
    public SecurityFilterChain handleSecurityChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                        configurer.requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST,"./api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
                );

        // use http basic authentication
        http.httpBasic(Customizer.withDefaults());

        //Disable CSRFx
        // in general not required for stateless REST APIS that use POST,PUT,DELETE.. etc
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}


/**
 *
 * hardCoded Authentication
 * post
 @Bean
 // This below bin represents the custom user name and password with defined roles
 // userDetailsManager is a method name that is of type InMemoryUserDetailsManager.. that's it.. you can have a diff name even it works.
 public InMemoryUserDetailsManager userDetailsManager (){
 UserDetails john = User.builder()
 .username("john")
 .password("{noop}test123")
 .roles("EMPLOYEE")
 .build();
 UserDetails mary = User.builder()
 .username("mary")
 .password("{noop}test123")
 .roles("EMPLOYEE","MANAGER")
 .build();
 UserDetails susan = User.builder()
 .username("susan")
 .password("{noop}test123")
 .roles("EMPLOYEE","MANAGER","ADMIN")
 .build();
 return new InMemoryUserDetailsManager(john,mary,susan);
 }
 **/