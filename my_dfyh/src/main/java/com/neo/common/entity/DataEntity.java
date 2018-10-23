package com.neo.common.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.neo.framework.util.DateUtil;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataEntity extends Entity {

	protected Date createTime;

	protected Date updateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setNewCreate() {
		this.createTime = new Date();
	}

	public void setCreateTimeIfNew() {
		if (this.createTime == null || "".equals(this.createTime)) {
			this.createTime = new Date();
		}
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUpdateTimeIfNew() {
		if (updateTime == null || "".equals(updateTime)) {
			this.updateTime = new Date();
		}
	}

	public String getCreateTimeFormat() {
		if (createTime != null) {
			return DateUtil.format(createTime, DateUtil.YMDHMS_PATTERN);
		} else {
			return "";
		}
	}

	public String getUpdateTimeFormat() {
		if (updateTime != null) {
			return DateUtil.format(updateTime, DateUtil.YMDHMS_PATTERN);
		} else {
			return "";
		}
	}

	public boolean isEmpty(String value) {
		if (value == null || "".equals(value) || "null".equals(value) || "undefiend".equals(value)) {
			return true;
		}
		return false;
	}

}
