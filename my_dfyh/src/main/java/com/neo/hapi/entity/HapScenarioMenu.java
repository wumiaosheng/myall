package com.neo.hapi.entity;

import com.neo.common.entity.MyBatisEntity;

public class HapScenarioMenu extends MyBatisEntity {

	private long menuId;
	private long scenarioId;

	public long getMenuId() {
		return menuId;
	}
	
	public void setMenuId(long menuId){ 
        this.menuId = menuId;
    }
	public long getScenarioId() {
		return scenarioId;
	}
	
	public void setScenarioId(long scenarioId){ 
        this.scenarioId = scenarioId;
    }

}