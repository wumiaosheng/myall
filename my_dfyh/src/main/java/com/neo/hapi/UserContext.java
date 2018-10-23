package com.neo.hapi;

import java.util.List;

import com.neo.hapi.entity.HapMenu;
import com.neo.hapi.entity.HapOrg;
import com.neo.hapi.entity.HapUser;
import com.neo.hapi.entity.HapUserAccount;

public class UserContext {
	private HapUser hapUser; 
	private HapUserAccount userAccount; 
	private HapMenu menu;
	private String token;
	private HapOrg currentHapOrg;// 当前登录人的部门
	private HapOrg recentCompany;// 当前登录人最近一级公司
	private List<Long> subOrgIds; // 子机构id列表
	
	public HapUser getHapUser() {
		return hapUser;
	}
	public void setHapUser(HapUser hapUser) {
		this.hapUser = hapUser;
	}
	public HapUserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(HapUserAccount userAccount) {
		this.userAccount = userAccount;
	}
	public HapMenu getMenu() {
		return menu;
	}
	public void setMenu(HapMenu menu) {
		this.menu = menu;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public HapOrg getCurrentHapOrg() {
		return currentHapOrg;
	}
	public void setCurrentHapOrg(HapOrg currentHapOrg) {
		this.currentHapOrg = currentHapOrg;
	}
	public HapOrg getRecentCompany() {
		return recentCompany;
	}
	public void setRecentCompany(HapOrg recentCompany) {
		this.recentCompany = recentCompany;
	}
	public List<Long> getSubOrgIds() {
		return subOrgIds;
	}
	public void setSubOrgIds(List<Long> subOrgIds) {
		this.subOrgIds = subOrgIds;
	}
	
}
