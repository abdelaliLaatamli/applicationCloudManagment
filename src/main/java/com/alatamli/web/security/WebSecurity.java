package com.alatamli.web.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
//			.antMatchers("/v2/api-docs" , "/v3/api-docs", "**/swagger-resources/**" ,"/swagger-ui/**", "/swagger-ui.html**","/webjars/**").permitAll()
			.antMatchers("/v2/api-docs" , "/swagger-resources/**" ,"/swagger-ui/**" ,"/swagger-ui.html**" , "/webjars/**" , "/springfox").permitAll()

			.anyRequest().authenticated().and() // others must be authenticated 
			.addFilter( getAuthenticationFilter() )
			.addFilter(new AuthorizationFilter(authenticationManager()))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
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
