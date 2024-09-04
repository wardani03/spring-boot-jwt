package com.example.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtDecoder jwtDecoder;

	@Autowired
	private JwtToPrincipalConverter jwtToPrincipalConverter;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		extractTokenFromRequest(request)
			.map(jwtDecoder::decode)
			.map(jwtToPrincipalConverter::convert)
			.map(UserPrincipalAuthenticationToken::new)
			.ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication));
		
		filterChain.doFilter(request, response);
	}

	private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token!= null && token.startsWith("Bearer ")) {
			return Optional.of(token.substring(7));
		}
		return Optional.empty();
	}

}
