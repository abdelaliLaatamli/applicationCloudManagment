package com.alatamli.web.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alatamli.web.SpringApplicationContext;
import com.alatamli.web.requests.UserLoginRequest;
import com.alatamli.web.services.AuthService;
import com.alatamli.web.shared.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			UserLoginRequest creds = new ObjectMapper().readValue(request.getInputStream()	, UserLoginRequest.class);
			
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail() , creds.getPassword() , new ArrayList<>() )
					);
		}catch (IOException e) {
			throw  new RuntimeException(e);
		}
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String username = ( (User) authResult.getPrincipal() ).getUsername();
		
		String token = Jwts
						.builder()
						.setSubject(username)
						.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
						.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET )
						.compact();
		
		AuthService authService = (AuthService) SpringApplicationContext.getBean("authService");
		
		UserDto userDto = authService.getUser(username);
							
		response.addHeader(SecurityConstants.HEADER_STRING , SecurityConstants.TOKEN_PREFIX + token );
		response.addHeader( "user_id" , userDto.getId()+"" );
		
		
		response.getWriter().write(" { \"token\" : \""+token+"\" , \"id\" :  \""+ userDto.getId() +"\" } ");
		
		//super.successfulAuthentication(request, response, chain, authResult);
	} 
	
	

}