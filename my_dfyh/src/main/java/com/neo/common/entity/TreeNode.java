package com.neo.common.entity;


public class TreeNode extends MyBatisEntity {

	private String name; // 名称
	private String parentId; // 父节点id，0为根节点

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String get_id() {
		return "" + id;
	}

}
