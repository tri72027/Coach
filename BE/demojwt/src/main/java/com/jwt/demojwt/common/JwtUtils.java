package com.jwt.demojwt.common;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.jwt.demojwt.service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	@Value("${bezkoder.app.jwtSecret}")
	private String jwtSecrect;

	@Value("${bezkoder.app.jwtExpirationMs}")
	private String jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecrect).compact();
	}
	public String getUserNameFromJwtToken(String token)
	{
		return Jwts.parser().setSigningKey(jwtSecrect).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateJwtToken(String authToken)
	{
		try {
			Jwts.parser().setSigningKey(jwtSecrect).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature {}"+ e.getMessage());
		}
		catch (MalformedJwtException e) {
			logger.error("Invalid JWT token "+ e.getMessage());
			// TODO: handle exception
		}
		catch (ExpiredJwtException e) {
			logger.error("Invalid token expired "+ e.getMessage());
			// TODO: handle exception
		}
		catch (UnsupportedJwtException e) {
			logger.error("Invalid token unsupported "+ e.getMessage());
			// TODO: handle exception
		}
		catch (IllegalArgumentException e) {
			logger.error("Invalid claims String is Empty "+ e.getMessage());
			// TODO: handle exception
		}
		return false;
	}
	
}