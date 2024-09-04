package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.JwtIssuer;
import com.example.model.LoginRequest;
import com.example.model.LoginResponse;

@RestController
public class AuthController {

	@Autowired
	private JwtIssuer jwtIssuer;

	@PostMapping("/auth/login")
	public LoginResponse login(@RequestBody LoginRequest request) {
		String token = jwtIssuer.issue(1L, request.getEmail(), List.of("USER"));
		LoginResponse response = new LoginResponse();
		response.setAccessToken(token);
		return response;

	}

}
