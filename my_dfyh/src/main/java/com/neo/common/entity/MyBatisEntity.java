package com.neo.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.neo.framework.util.DateUtil;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MyBatisEntity implements Serializable, Cloneable {

	protected long id;

	protected long createUserId;
	protected Timestamp createTime;
	protected long updateUserId;
	protected Timestamp updateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIdIfNew() {
		if (id == 0) {
			setId(getLongUUID());
		}
	}

	public void setNewId() {
		this.id = getLongUUID(); 
	}

	public static long getLongUUID() {
		Long uuid;
		do {
			uuid = Math.abs(UUID.randomUUID().getMostSignificantBits());
		} while (uuid <= 0L);
		return uuid;
	}

	@JsonSerialize(using = EHDateTimeSerializer.class)
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setNewCreate() {
		this.createTime = DateUtil.currentTimestamp();
	}

	public void setCreateTimeIfNew() {
		if (this.createTime == null || "".equals(this.createTime)) {
			setNewCreate();
		}
	}

	public long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}

	public long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(long updateUserId) {
		this.updateUserId = updateUserId;
	}

	@JsonSerialize(using = EHDateTimeSerializer.class)
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public void setUpdateTimeIfNew() {
		if (updateTime == null || "".equals(updateTime)) {
			this.updateTime = DateUtil.currentTimestamp();
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

	public String getSequenceName() {
		return "S_" + this.getClass().getSimpleName().toUpperCase();
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
