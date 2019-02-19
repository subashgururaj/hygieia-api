package com.capitalone.dashboard.auth.access;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize(DashboardOwnerOrAdmin.IS_DASHBOARD_OWNER_OR_ADMIN)
public @interface DashboardOwnerOrAdmin {

	String IS_DASHBOARD_OWNER_OR_ADMIN = "@methodLevelSecurityHandler.isOwnerOfDashboard(#id) or hasRole('ROLE_ADMIN')";
	
}
