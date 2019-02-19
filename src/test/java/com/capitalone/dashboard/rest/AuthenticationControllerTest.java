package com.capitalone.dashboard.rest;

import com.capitalone.dashboard.auth.AuthProperties;
import com.capitalone.dashboard.model.AuthType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {
	
	private final AuthType standardAuthType = AuthType.STANDARD;
	private final AuthType ldapAuthType = AuthType.LDAP;

	@Mock
	private AuthProperties authProperties;
	
	@InjectMocks
	private AuthenticationController authController;

	@Test
	public void multipleAuthTypes() {
		List<AuthType> expectedReturn = new ArrayList<>();
		
		expectedReturn.add(standardAuthType);
		expectedReturn.add(ldapAuthType);

		when(authProperties.getAuthenticationProviders()).thenReturn(expectedReturn);
		
		List<AuthType> result = authController.getAuthenticationProviders();
		
		assertNotNull(result);
		assertEquals(result, expectedReturn);
		verify(authProperties).getAuthenticationProviders();
	}
	
	@Test
	public void oneType() {
		List<AuthType> expectedReturn = new ArrayList<>();

		expectedReturn.add(ldapAuthType);

		when(authProperties.getAuthenticationProviders()).thenReturn(expectedReturn);
		
		List<AuthType> result = authController.getAuthenticationProviders();
		
		assertNotNull(result);
		assertEquals(result, expectedReturn);
		verify(authProperties).getAuthenticationProviders();
	}
	
	@Test
	public void zeroTypes() {
		List<AuthType> expectedReturn = new ArrayList<>();

		when(authProperties.getAuthenticationProviders()).thenReturn(expectedReturn);
		
		List<AuthType> result = authController.getAuthenticationProviders();
		
		assertNotNull(result);
		assertEquals(result, expectedReturn);
		verify(authProperties).getAuthenticationProviders();
	}
	
	
}
