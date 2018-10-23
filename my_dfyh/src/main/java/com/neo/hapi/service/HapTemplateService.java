package com.neo.hapi.service;

import java.util.List;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.entity.HapTemplate;

public interface HapTemplateService {
   
	public ReturnStatus update(HapTemplate hapTemplate);
	
    public ReturnStatus deleteById(long id);
	
	public HapTemplate findById(long id);
	
	public List<HapTemplate> findAll();
	
	public FlipInfo<HapTemplate> findByPage(FlipInfo<HapTemplate> flp);
	

    
	
}
