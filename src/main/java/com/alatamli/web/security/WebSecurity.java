package com.alatamli.web.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.alatamli.web.services.AuthService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final AuthService useDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(AuthService useDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.useDetailsService = useDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.cors().and()
			.csrf().disable()
			.authorizeRequests()
				// .antMatchers(HttpMethod.POST , "/users/**").permitAll() // request don't need be authenticated
				.anyRequest().authenticated().and() // others must be authenticated 
			// .addFilter(new AuthenticationFilter(authenticationManager()));
			.addFilter( getAuthenticationFilter() );
	}
	
	
	protected AuthenticationFilter getAuthenticationFilter() throws Exception {
		
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/auth/login");
		
		return filter ;
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(useDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}


	
	
	
	

}
