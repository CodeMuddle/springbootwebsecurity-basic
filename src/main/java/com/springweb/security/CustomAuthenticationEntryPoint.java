package com.springweb.security;
/*
 * 
 * the actual Entry point,
 * which will get triggerd if authentication failed.
 * You can customize it to send custom content in response.
 * 
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
	
	
	// triggers if the authentication fails
	public void commence(final HttpServletRequest request, final HttpServletResponse response, 
			final AuthenticationException authException) throws IOException, ServletException{
		 response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
		 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		 
		 PrintWriter writer = response.getWriter();
		 writer.println("HttpStatus 401: "+authException.getMessage());
		 
		
		
	}
	 @Override
	    public void afterPropertiesSet() throws Exception {
	        setRealmName("MY_TEST_REALM");
	        super.afterPropertiesSet();
	    }

}
