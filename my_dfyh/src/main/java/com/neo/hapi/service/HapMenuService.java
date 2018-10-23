package com.neo.hapi.service;

import java.util.List;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.MyBatisEntity;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.entity.HapMenu;

public interface HapMenuService {
	
	public ReturnStatus update(HapMenu hapMenu);
    
	public ReturnStatus deleteById(long id);
	
	public HapMenu findById(long id);
	
	public List<HapMenu> findAll();
	
	public FlipInfo<HapMenu> findByPage(FlipInfo<HapMenu> flp);
	
	public List<HapMenu> findAll2Tree();
}
