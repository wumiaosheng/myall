package com.neo.hapi.service;

import java.util.List;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.entity.HapRole;
import com.neo.hapi.entity.HapSystem;



public interface  HapSystemService {

	public ReturnStatus update(HapSystem hapSystem);
	
	public List<HapSystem> findAll();
	
	public HapSystem findById(long id);
	
	public ReturnStatus deleteById(long id);
	
	public FlipInfo<HapSystem> findByPage(FlipInfo<HapSystem> fpi);
}
