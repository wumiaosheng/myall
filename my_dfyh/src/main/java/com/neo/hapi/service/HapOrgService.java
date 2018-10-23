package com.neo.hapi.service;

import java.util.List;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.entity.HapOrg;

public interface HapOrgService {
  
public ReturnStatus update(HapOrg hapOrg);
    
	public ReturnStatus deleteById(long id);
	
	public HapOrg findById(long id);
	
	public List<HapOrg> findAll();
	
	public FlipInfo<HapOrg> findByPage(FlipInfo<HapOrg> flp);
	
	
	public List<HapOrg> findAll2Tree();
}
