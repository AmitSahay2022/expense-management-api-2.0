package com.google.expensemanagementapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {	
	private UserDetailsService userDetailsService;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(auth -> {
			auth.requestMatchers(HttpMethod.POST, "/api/users/register").permitAll();
			auth.requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN");
			auth.requestMatchers(HttpMethod.GET, "/api/users/all").hasRole("ADMIN");
			auth.requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("USER");
			auth.anyRequest().authenticated();
		});
		httpSecurity.csrf(csrf -> csrf.disable());
		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.formLogin(Customizer.withDefaults());
		return httpSecurity.build();
	}
}
