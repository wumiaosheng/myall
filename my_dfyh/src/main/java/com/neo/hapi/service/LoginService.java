package com.neo.hapi.service;

import com.neo.hapi.UserContext;
import com.neo.hapi.entity.HapUserAccount;

public interface LoginService {
	
	public UserContext transLogin(HapUserAccount hua);
	
}
