package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserPrincipal principal;

	public void setPrincipal(UserPrincipal principal) {
		this.principal = principal;
	}

	public UserPrincipalAuthenticationToken(UserPrincipal principal) {
		super(principal.getAuthorities());
		this.principal = principal;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}
}
