package com.neo.hapi.service;

import java.util.List;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.entity.HapUserAccount;

public interface HapAccountService {
	
	public ReturnStatus update(HapUserAccount hapUserAccount);
    
	public ReturnStatus deleteById(long id);
	
	public HapUserAccount findById(long id);
	
	public List<HapUserAccount> findAll();
	
	public FlipInfo<HapUserAccount> findByPage(FlipInfo<HapUserAccount> flp);
	
}
