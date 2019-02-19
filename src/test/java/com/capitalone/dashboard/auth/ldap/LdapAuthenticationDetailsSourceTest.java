package com.capitalone.dashboard.auth.ldap;

import com.capitalone.dashboard.model.AuthType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LdapAuthenticationDetailsSourceTest {

	@Test
	public void test() {
		LdapAuthenticationDetailsSource detailsSource = new LdapAuthenticationDetailsSource();
		assertEquals(AuthType.LDAP, detailsSource.buildDetails(null));
	}

}
