package com.capitalone.dashboard.auth.sso;

import com.capitalone.dashboard.auth.ldap.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SsoAuthenticationServiceImpl implements SsoAuthenticationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SsoAuthenticationServiceImpl.class);

	@Autowired
	private SsoAuthenticationUtil ssoAuthenticationUtil;
	
	@Override
	public Authentication getAuthenticationFromHeaders(Map<String, String> requestHeadersMap) {
		return this.getAuthenticationDataFromHeaders(requestHeadersMap);
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(Collection<String> roles) {
		return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
	}

	@SuppressWarnings({"unchecked", "PMD.LooseCoupling"})
	private Authentication getAuthenticationDataFromHeaders(Map<String, String> headersMap) {
		CustomUserDetails customUserDetails = null;
		try {
			if (headersMap != null) {
				String cookiesHeader = headersMap.get("cookiesheader");
				
				HashMap<String,String> userInfoDataMap = new ObjectMapper().readValue(cookiesHeader, HashMap.class);
				
				int count = 0;
				for(Map.Entry<String, String> stringStringEntry : headersMap.entrySet()) {
					LOGGER.debug("Header (" + ++count + ".) : " + stringStringEntry.getKey() + ", value : " + stringStringEntry.getValue());
				}
				LOGGER.debug("cookiesHeader : ==> =====> =======>  " + cookiesHeader);
				
				customUserDetails = ssoAuthenticationUtil.createUser(userInfoDataMap);
				return SsoAuthenticationUtil.createSuccessfulAuthentication(customUserDetails);
			} else {
				LOGGER.error("SsoAuthenticationServiceImpl.getAuthenticationDataFromHeaders() :=> userInfo is Null");
			}
		} catch (Exception exception) {
			LOGGER.error("SsoAuthenticationServiceImpl.getAuthenticationDataFromHeaders() :=> Exception :"
					+ exception);
		}
		return null;
	}

}
