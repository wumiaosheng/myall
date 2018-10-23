package com.neo.hapi.entity;

import com.neo.common.entity.MyBatisEntity;

public class HapUserAccount extends MyBatisEntity {

	private java.lang.String enable;
	private java.lang.String account;
	private java.lang.String password;
	private java.sql.Timestamp starttime;
	private java.sql.Timestamp endtime;
	private java.lang.String accountType;
	private java.sql.Timestamp lastlogindate;
	private java.lang.String lastloginip;
	private java.lang.String passquestion;
	private java.lang.String passanswer;
	private java.lang.String delflag;
	private long createorgid;
	private java.lang.String memo;
	private java.lang.String temp1;
	private java.lang.String temp2;

	public java.lang.String getEnable() {
		return enable;
	}
	
	public void setEnable(java.lang.String enable){ 
        this.enable = enable;
    }
	public java.lang.String getAccount() {
		return account;
	}
	
	public void setAccount(java.lang.String account){ 
        this.account = account;
    }
	public java.lang.String getPassword() {
		return password;
	}
	
	public void setPassword(java.lang.String password){ 
        this.password = password;
    }
	public java.sql.Timestamp getStarttime() {
		return starttime;
	}
	
	public void setStarttime(java.sql.Timestamp starttime){ 
        this.starttime = starttime;
    }
	public java.sql.Timestamp getEndtime() {
		return endtime;
	}
	
	public void setEndtime(java.sql.Timestamp endtime){ 
        this.endtime = endtime;
    }
	public java.lang.String getAccountType() {
		return accountType;
	}
	
	public void setAccountType(java.lang.String accountType){ 
        this.accountType = accountType;
    }
	public java.sql.Timestamp getLastlogindate() {
		return lastlogindate;
	}
	
	public void setLastlogindate(java.sql.Timestamp lastlogindate){ 
        this.lastlogindate = lastlogindate;
    }
	public java.lang.String getLastloginip() {
		return lastloginip;
	}
	
	public void setLastloginip(java.lang.String lastloginip){ 
        this.lastloginip = lastloginip;
    }
	public java.lang.String getPassquestion() {
		return passquestion;
	}
	
	public void setPassquestion(java.lang.String passquestion){ 
        this.passquestion = passquestion;
    }
	public java.lang.String getPassanswer() {
		return passanswer;
	}
	
	public void setPassanswer(java.lang.String passanswer){ 
        this.passanswer = passanswer;
    }
	public java.lang.String getDelflag() {
		return delflag;
	}
	
	public void setDelflag(java.lang.String delflag){ 
        this.delflag = delflag;
    }
	public long getCreateorgid() {
		return createorgid;
	}
	
	public void setCreateorgid(long createorgid){ 
        this.createorgid = createorgid;
    }
	public java.lang.String getMemo() {
		return memo;
	}
	
	public void setMemo(java.lang.String memo){ 
        this.memo = memo;
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