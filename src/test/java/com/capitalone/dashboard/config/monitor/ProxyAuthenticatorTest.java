package com.capitalone.dashboard.config.monitor;

import org.junit.Test;

import java.net.PasswordAuthentication;

import static org.junit.Assert.assertEquals;

public class ProxyAuthenticatorTest {

	private ProxyAuthenticator proxyAuthenticator;
	
	@Test
	public void testGetPasswordAuthentication() {
		PasswordAuthentication expected = new PasswordAuthentication("username", "password".toCharArray());
		proxyAuthenticator = new ProxyAuthenticator(expected);
		
		assertEquals(expected, proxyAuthenticator.getPasswordAuthentication());
	}
	
}
