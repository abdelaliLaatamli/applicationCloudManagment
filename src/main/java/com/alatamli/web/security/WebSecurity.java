package com.alatamli.web.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.cors().and()
			.csrf().disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST , "/users/**").permitAll() // request don't need be authenticated
				.anyRequest().authenticated(); // others must be authenticated
			
		
		
		
	}
	
	

}
