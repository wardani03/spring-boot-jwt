package com.example.config;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtToPrincipalConverter {

	public UserPrincipal convert(DecodedJWT jwt) {
		UserPrincipal principal = new UserPrincipal();
		principal.setUserId(Long.valueOf(jwt.getSubject()));
		principal.setEmail(jwt.getClaim("email").asString());
		principal.setAuthorities(extractAutoritiesFromClaim(jwt));
		return principal;
	}

	private List<SimpleGrantedAuthority> extractAutoritiesFromClaim(DecodedJWT jwt) {
		Claim claim = jwt.getClaim("role");
		return claim.isNull() || claim.isMissing() ? List.of() : claim.asList(SimpleGrantedAuthority.class);
	}

}
