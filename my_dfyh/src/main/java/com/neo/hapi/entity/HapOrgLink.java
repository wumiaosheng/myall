package com.neo.hapi.entity;

import com.neo.common.entity.MyBatisEntity;

public class HapOrgLink extends MyBatisEntity {

	private long parent;
	private long child;

	public long getParent() {
		return parent;
	}
	
	public void setParent(long parent){ 
        this.parent = parent;
    }
	public long getChild() {
		return child;
	}
	
	public void setChild(long child){ 
        this.child = child;
    }

}