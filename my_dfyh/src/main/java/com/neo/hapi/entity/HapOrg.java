package com.neo.hapi.entity;

import com.neo.common.entity.MyBatisEntity;

public class HapOrg extends MyBatisEntity {

	private java.lang.String orgname;
	private java.lang.String orgabbr;
	private java.lang.String orgcode;
	private java.lang.String levelcode;
	private int orglevel;
	private int orgorder;
	private java.lang.String contact;
	private java.lang.String description;
	private long parentid;
	private java.lang.String orgtype;
	private java.lang.String region;
	private java.lang.String version;
	private int isdelete;
	private long createorgid;
	private java.lang.String temp1;
	private java.lang.String temp2;

	public java.lang.String getOrgname() {
		return orgname;
	}
	
	public void setOrgname(java.lang.String orgname){ 
        this.orgname = orgname;
    }
	public java.lang.String getOrgabbr() {
		return orgabbr;
	}
	
	public void setOrgabbr(java.lang.String orgabbr){ 
        this.orgabbr = orgabbr;
    }
	public java.lang.String getOrgcode() {
		return orgcode;
	}
	
	public void setOrgcode(java.lang.String orgcode){ 
        this.orgcode = orgcode;
    }
	public java.lang.String getLevelcode() {
		return levelcode;
	}
	
	public void setLevelcode(java.lang.String levelcode){ 
        this.levelcode = levelcode;
    }
	public int getOrglevel() {
		return orglevel;
	}
	
	public void setOrglevel(int orglevel){ 
        this.orglevel = orglevel;
    }
	public int getOrgorder() {
		return orgorder;
	}
	
	public void setOrgorder(int orgorder){ 
        this.orgorder = orgorder;
    }
	public java.lang.String getContact() {
		return contact;
	}
	
	public void setContact(java.lang.String contact){ 
        this.contact = contact;
    }
	public java.lang.String getDescription() {
		return description;
	}
	
	public void setDescription(java.lang.String description){ 
        this.description = description;
    }
	public long getParentid() {
		return parentid;
	}
	
	public void setParentid(long parentid){ 
        this.parentid = parentid;
    }
	public java.lang.String getOrgtype() {
		return orgtype;
	}
	
	public void setOrgtype(java.lang.String orgtype){ 
        this.orgtype = orgtype;
    }
	public java.lang.String getRegion() {
		return region;
	}
	
	public void setRegion(java.lang.String region){ 
        this.region = region;
    }
	public java.lang.String getVersion() {
		return version;
	}
	
	public void setVersion(java.lang.String version){ 
        this.version = version;
    }
	public int getIsdelete() {
		return isdelete;
	}
	
	public void setIsdelete(int isdelete){ 
        this.isdelete = isdelete;
    }
	public long getCreateorgid() {
		return createorgid;
	}
	
	public void setCreateorgid(long createorgid){ 
        this.createorgid = createorgid;
    }
	public java.lang.String getTemp1() {
		return temp1;
	}
	
	public void setTemp1(java.lang.String temp1){ 
        this.temp1 = temp1;
    }
	public java.lang.String getTemp2() {
		return temp2;
	}
	
	public void setTemp2(java.lang.String temp2){ 
        this.temp2 = temp2;
    }

}