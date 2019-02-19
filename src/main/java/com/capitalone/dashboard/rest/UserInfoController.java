package com.capitalone.dashboard.rest;

import com.capitalone.dashboard.model.UserInfo;
import com.capitalone.dashboard.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserInfoController {
    
	private final UserInfoService userInfoService;
	
	@Autowired
	public UserInfoController(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public Collection<UserInfo> getUsers() {
        return userInfoService.getUsers();
    }
	
}
