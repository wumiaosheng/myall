package com.neo.hapi.entity;

import com.neo.common.entity.MyBatisEntity;

public class HapTemplate extends MyBatisEntity {

	private java.lang.String templatename;
	private java.lang.String indexUrl;
	private java.lang.String pictureUrl;

	public java.lang.String getTemplatename() {
		return templatename;
	}
	
	public void setTemplatename(java.lang.String templatename){ 
        this.templatename = templatename;
    }
	public java.lang.String getIndexUrl() {
		return indexUrl;
	}
	
	public void setIndexUrl(java.lang.String indexUrl){ 
        this.indexUrl = indexUrl;
    }
	public java.lang.String getPictureUrl() {
		return pictureUrl;
	}
	
	public void setPictureUrl(java.lang.String pictureUrl){ 
        this.pictureUrl = pictureUrl;
    }

}