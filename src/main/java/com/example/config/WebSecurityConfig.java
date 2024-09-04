package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;

	@Bean
	SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

		http.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.formLogin(login -> login
						.disable())
				.securityMatcher("/**")
				.authorizeHttpRequests(registry -> registry
						.requestMatchers("/").permitAll()
						.requestMatchers("/auth/login").permitAll()
						.anyRequest().authenticated());

		return http.build();
	}
}
