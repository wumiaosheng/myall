package com.neo.hapi.entity;

import com.neo.common.entity.MyBatisEntity;

public class HapRoleFunc extends MyBatisEntity {

	private long funcId;
	private long roleId;

	public long getFuncId() {
		return funcId;
	}
	
	public void setFuncId(long funcId){ 
        this.funcId = funcId;
    }
	public long getRoleId() {
		return roleId;
	}
	
	public void setRoleId(long roleId){ 
        this.roleId = roleId;
    }

}