package com.capitalone.dashboard.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationResultHandlerTest {
	
	@Mock
	private AuthenticationResponseService responseService;
	
	@Mock
	private HttpServletResponse response;
	
	@Mock
	private Authentication auth;
	
	@InjectMocks
	private AuthenticationResultHandler handler;

	@Test
	public void testOnSucess() throws IOException, ServletException {
		handler.onAuthenticationSuccess(null, response, auth);
		
		verify(responseService).handle(response, auth);
	}

}
