package com.neo.hapi.entity;

import com.neo.common.entity.MyBatisEntity;

public class HapOrgRole extends MyBatisEntity {

	private long roleId;
	private long orgId;
	private long createorgid;

	public long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(long roleId){ 
        this.roleId = roleId;
    }
	public long getOrgId() {
		return orgId;
	}
	
	public void setOrgId(long orgId){ 
        this.orgId = orgId;
    }
	public long getCreateorgid() {
		return createorgid;
	}
	
	public void setCreateorgid(long createorgid){ 
        this.createorgid = createorgid;
    }

}