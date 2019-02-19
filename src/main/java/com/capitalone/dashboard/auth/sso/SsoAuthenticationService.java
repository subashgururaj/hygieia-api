package com.capitalone.dashboard.auth.sso;

import org.springframework.security.core.Authentication;

import java.util.Map;

public interface SsoAuthenticationService {

	Authentication getAuthenticationFromHeaders(Map<String, String> requestHeadersMap);
}
