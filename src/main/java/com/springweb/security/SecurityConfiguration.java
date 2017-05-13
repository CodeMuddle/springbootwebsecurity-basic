package com.springweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * 
 * @author manshi
 * security configuration that ensures that only authenticated users can see the secret book Information
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private static final String REALM = "MY_TEST_REALM";
	
	// set authenticted users
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("Nalin").password("Adhikari123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("Keshab").password("security123").roles("USER");
		
	}
	
	
	//this method contains authentictionentrypoint, httpbasic relam name becoz i havent used loginform for authentiation username and password
	@Override
	public void configure(HttpSecurity http) throws Exception{

	      http.csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/book/**").hasRole("ADMIN")
	        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
	        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
		
	}
	
	// entrypoint for verification of authorization 
	@Bean
	public AuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}

}
