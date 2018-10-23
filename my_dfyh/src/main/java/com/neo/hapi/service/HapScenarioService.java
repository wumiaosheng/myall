package com.neo.hapi.service;

import java.util.List;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.entity.HapScenario;

public interface HapScenarioService {
	
	public ReturnStatus update(HapScenario hapScenario);
    
	public ReturnStatus deleteById(long id);
	
	public HapScenario findById(long id);
	
	public List<HapScenario> findAll();
	
	public FlipInfo<HapScenario> findByPage(FlipInfo<HapScenario> flp);
	
}
