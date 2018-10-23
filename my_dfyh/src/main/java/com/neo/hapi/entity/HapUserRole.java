package com.neo.hapi.entity;

import com.neo.common.entity.MyBatisEntity;

public class HapUserRole extends MyBatisEntity {

	private long roleId;
	private long userId;
	private long createorgid;

	public long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(long roleId){ 
        this.roleId = roleId;
    }
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId){ 
        this.userId = userId;
    }
	public long getCreateorgid() {
		return createorgid;
	}
	
	public void setCreateorgid(long createorgid){ 
        this.createorgid = createorgid;
    }

}