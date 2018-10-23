package com.neo.hapi.service;

import java.util.List;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.entity.HapUser;

public interface HapUserService {
	
	public ReturnStatus update(HapUser hapUser);
    
	public ReturnStatus deleteById(long id);
	
	public HapUser findById(long id);
	
	public List<HapUser> findAll();
	
	public FlipInfo<HapUser> findByPage(FlipInfo<HapUser> flp);
	
}
