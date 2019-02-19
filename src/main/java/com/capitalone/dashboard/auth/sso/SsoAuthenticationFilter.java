package com.capitalone.dashboard.auth.sso;

import com.capitalone.dashboard.auth.AuthenticationResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;


public class SsoAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(SsoAuthenticationFilter.class);

	private final AtomicReference<SsoAuthenticationService> ssoAuthenticationService = new AtomicReference<>();

	public SsoAuthenticationFilter(String path, AuthenticationManager authManager, AuthenticationResultHandler authenticationResultHandler, SsoAuthenticationService ssoAuthenticationService) {
		super();
		setAuthenticationManager(authManager);
		setAuthenticationSuccessHandler(authenticationResultHandler);
		setFilterProcessesUrl(path);
		this.ssoAuthenticationService.set(ssoAuthenticationService);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		Authentication authenticated = null;
		Map<String, String> headersMap = new HashMap<>();
		
		if(request.getHeader("cookiesheader") == null) {
			LOGGER.debug("no header found for user details");
			return null;
		}
		
		Enumeration<String> headers = request.getHeaderNames();
		
		if(headers == null || !headers.hasMoreElements()) {
			return null;
		}
		while(headers.hasMoreElements()) {
			String headerName = headers.nextElement();
			headersMap.put(headerName, request.getHeader(headerName));
		}
		
		authenticated = this.ssoAuthenticationService.get().getAuthenticationFromHeaders(headersMap);
    	return authenticated;
	}
}
