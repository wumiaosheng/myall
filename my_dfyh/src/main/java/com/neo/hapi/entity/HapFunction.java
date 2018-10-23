package com.neo.hapi.entity;

import com.neo.common.entity.MyBatisEntity;

public class HapFunction extends MyBatisEntity {

	private java.lang.String funcname;
	private java.lang.String navurl;
	private java.lang.String funccode;
	private int funcorder;
	private long systemId;

	public java.lang.String getFuncname() {
		return funcname;
	}
	
	public void setFuncname(java.lang.String funcname){ 
        this.funcname = funcname;
    }
	public java.lang.String getNavurl() {
		return navurl;
	}
	
	public void setNavurl(java.lang.String navurl){ 
        this.navurl = navurl;
    }
	public java.lang.String getFunccode() {
		return funccode;
	}
	
	public void setFunccode(java.lang.String funccode){ 
        this.funccode = funccode;
    }
	public int getFuncorder() {
		return funcorder;
	}
	
	public void setFuncorder(int funcorder){ 
        this.funcorder = funcorder;
    }
	public long getSystemId() {
		return systemId;
	}
	
	public void setSystemId(long systemId){ 
        this.systemId = systemId;
    }

}