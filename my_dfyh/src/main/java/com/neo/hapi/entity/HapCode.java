package com.neo.hapi.entity;

import com.neo.common.entity.MyBatisEntity;


public class HapCode extends MyBatisEntity {

	private java.lang.String codevalue;
	private java.lang.String codetext;
	private java.lang.String codetype;
	private int codeorder;

	public java.lang.String getCodevalue() {
		return codevalue;
	}
	
	public void setCodevalue(java.lang.String codevalue){ 
        this.codevalue = codevalue;
    }
	public java.lang.String getCodetext() {
		return codetext;
	}
	
	public void setCodetext(java.lang.String codetext){ 
        this.codetext = codetext;
    }
	public java.lang.String getCodetype() {
		return codetype;
	}
	
	public void setCodetype(java.lang.String codetype){ 
        this.codetype = codetype;
    }
	public int getCodeorder() {
		return codeorder;
	}
	
	public void setCodeorder(int codeorder){ 
        this.codeorder = codeorder;
    }

}