package com.neo.hapi.entity;

import com.neo.common.entity.MyBatisEntity;

public class HapScenario extends MyBatisEntity {

	private java.lang.String scenename;
	private long tempateId;
	private java.lang.String description;
	
	////冗余代码
	private String templatename;

	public java.lang.String getScenename() {
		return scenename;
	}
	
	public void setScenename(java.lang.String scenename){ 
        this.scenename = scenename;
    }
	public long getTempateId() {
		return tempateId;
	}
	
	public void setTempateId(long tempateId){ 
        this.tempateId = tempateId;
    }
	public java.lang.String getDescription() {
		return description;
	}
	
	public void setDescription(java.lang.String description){ 
        this.description = description;
    }

	public String getTemplatename() {
		return templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

}