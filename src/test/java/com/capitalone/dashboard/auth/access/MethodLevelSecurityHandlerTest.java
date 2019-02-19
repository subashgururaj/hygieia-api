package com.capitalone.dashboard.auth.access;

import com.capitalone.dashboard.auth.AuthenticationFixture;
import com.capitalone.dashboard.model.AuthType;
import com.capitalone.dashboard.model.Dashboard;
import com.capitalone.dashboard.model.DashboardType;
import com.capitalone.dashboard.model.Owner;
import com.capitalone.dashboard.model.ScoreDisplayType;
import com.capitalone.dashboard.repository.DashboardRepository;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MethodLevelSecurityHandlerTest {

	private static final String USERNAME = "username";
	private static final String SOME_OTHER_USER = "someotheruser";
	private static final String configItemAppName = "ASVTEST";
	private static final String configItemComponentName = "BAPTEST";
	
	@InjectMocks
	private MethodLevelSecurityHandler handler;
	
	@Mock
	private DashboardRepository dashboardRepository;
	
	@Before
	public void setup() {
		SecurityContextHolder.clearContext();
	}
	
	@Test
	public void testIsOwnerOfDashboard_noDashFound() {
		when(dashboardRepository.findOne(any(ObjectId.class))).thenReturn(null);
		
		assertFalse(handler.isOwnerOfDashboard(new ObjectId()));
	}
	
	@Test
	public void testIsOwnerOfDashboard_legacyDashFound() {
		initiateSecurityContext();
		List<String> activeWidgets = new ArrayList<>();
		Dashboard dashboard = new Dashboard("team", "title", null, null, DashboardType.Team, configItemAppName,configItemComponentName,activeWidgets, false, ScoreDisplayType.HEADER);
		dashboard.setOwner(USERNAME);
		when(dashboardRepository.findOne(any(ObjectId.class))).thenReturn(dashboard);
		
		assertTrue(handler.isOwnerOfDashboard(new ObjectId()));
	}

	@Test
	public void testIsOwnerOfDashboard_newDashFound() {
		initiateSecurityContext();
		List<String> activeWidgets = new ArrayList<>();
		Dashboard dashboard = new Dashboard("team", "title", null, new Owner(USERNAME, AuthType.STANDARD), DashboardType.Team, configItemAppName,configItemComponentName,activeWidgets, false, ScoreDisplayType.HEADER);
		when(dashboardRepository.findOne(any(ObjectId.class))).thenReturn(dashboard);
		
		assertTrue(handler.isOwnerOfDashboard(new ObjectId()));
	}
	
	@Test
	public void testIsNotOwnerOfDashboard() {
		initiateSecurityContext();
		List<String> activeWidgets = new ArrayList<>();
		Dashboard dashboard = new Dashboard("team", "title", null, null, DashboardType.Team,configItemAppName,configItemComponentName,activeWidgets, false, ScoreDisplayType.HEADER);
		dashboard.setOwner(SOME_OTHER_USER);
		when(dashboardRepository.findOne(any(ObjectId.class))).thenReturn(dashboard);
		
		assertFalse(handler.isOwnerOfDashboard(new ObjectId()));
	}
	
	private void initiateSecurityContext() {
		AuthenticationFixture.createAuthentication(USERNAME);
	}
}
