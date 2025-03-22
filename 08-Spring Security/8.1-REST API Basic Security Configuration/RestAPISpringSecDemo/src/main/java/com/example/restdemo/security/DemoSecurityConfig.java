package com.example.restdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

	// adding users
    @Bean
    InMemoryUserDetailsManager userDetailsManager() {
		
		UserDetails john = User.builder()
				.username("john")
				.password("{noop}john@123")
				.roles("EMPLOYEE")
				.build();
		

		UserDetails mary = User.builder()
				.username("mary")
				.password("{noop}mary@123")
				.roles("EMPLOYEE","MANAGER")
				.build();
		

		UserDetails susan = User.builder()
				.username("susan")
				.password("{noop}susan@123")
				.roles("EMPLOYEE","MANAGER","ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(john, mary, susan);
	}

    
    // restrict access based on http method and end point / path
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(configurer ->configurer
				.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
				.requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
				.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
				.requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
				.requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
				);
		//use HTTP basic authentication
		http.httpBasic(Customizer.withDefaults());
		
		//disable CSRF
		http.csrf(csrf->csrf.disable());
		return http.build();
	}
}
