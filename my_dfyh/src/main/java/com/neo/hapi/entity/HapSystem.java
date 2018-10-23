package com.neo.hapi.entity;

import com.neo.common.entity.MyBatisEntity;

public class HapSystem extends MyBatisEntity {

	private java.lang.String sysname;
	private java.lang.String description;
	private java.lang.String rooturi;

	public java.lang.String getSysname() {
		return sysname;
	}
	
	public void setSysname(java.lang.String sysname){ 
        this.sysname = sysname;
    }
	public java.lang.String getDescription() {
		return description;
	}
	
	public void setDescription(java.lang.String description){ 
        this.description = description;
    }
	public java.lang.String getRooturi() {
		return rooturi;
	}
	
	public void setRooturi(java.lang.String rooturi){ 
        this.rooturi = rooturi;
    }

}