package com.neo.hapi.entity;

import com.neo.common.entity.MyBatisEntity;

public class HapUserOrg extends MyBatisEntity {

	private long orgId;
	private long userId;

	public long getOrgId() {
		return orgId;
	}
	
	public void setOrgId(long orgId){ 
        this.orgId = orgId;
    }
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId){ 
        this.userId = userId;
    }

}