package com.neo.hapi.service;

import java.util.List;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.entity.HapRole;



public interface  HapRoleService {

	public List<HapRole> findRoleByUserId();
	
	public List<HapRole> findAll();
	
	public HapRole findById(long id);
	
	public ReturnStatus update(HapRole hr);
	
	public FlipInfo<HapRole> findByPage(FlipInfo<HapRole> fpi);
	
	
	public ReturnStatus deleteById(long id);
}
