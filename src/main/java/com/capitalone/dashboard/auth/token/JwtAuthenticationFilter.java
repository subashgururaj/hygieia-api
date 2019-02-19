package com.capitalone.dashboard.auth.token;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class JwtAuthenticationFilter extends GenericFilterBean {

	private final AtomicReference<TokenAuthenticationService> tokenAuthenticationService = new AtomicReference<>();
	

	public JwtAuthenticationFilter(TokenAuthenticationService tokenAuthenticationService){
		this.tokenAuthenticationService.set(tokenAuthenticationService);
	}
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        if (request != null) {
            String authHeader = ((HttpServletRequest) request).getHeader("Authorization");
            if (authHeader == null || authHeader.startsWith("apiToken ")) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        Authentication authentication = tokenAuthenticationService.get().getAuthentication((HttpServletRequest)request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }
}