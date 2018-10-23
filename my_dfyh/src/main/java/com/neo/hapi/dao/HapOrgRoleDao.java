package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapOrgRole;

public interface HapOrgRoleDao {

	@Insert("insert into hap_org_role (ID, ROLE_ID, ORG_ID, CREATE_TIME, CREATE_USER_ID, UPDATE_USER_ID, UPDATE_TIME, CREATEORGID) values(#{id}, #{roleId}, #{orgId}, #{createTime,jdbcType=TIMESTAMP}, #{createUserId}, #{updateUserId}, #{updateTime,jdbcType=TIMESTAMP}, #{createorgid})")
	public int insert(HapOrgRole hapOrgRole);
	
	@Delete("delete from hap_org_role where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_org_role set ID=#{id}, ROLE_ID=#{roleId}, ORG_ID=#{orgId}, CREATE_TIME=#{createTime,jdbcType=TIMESTAMP}, CREATE_USER_ID=#{createUserId}, UPDATE_USER_ID=#{updateUserId}, UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP}, CREATEORGID=#{createorgid} where id=#{id}")
	public int update(HapOrgRole hapOrgRole);
	
	@Select("select * from hap_org_role where id = #{id}")
	public HapOrgRole findById(long id);

	@Select("select * from hap_org_role")
	public List<HapOrgRole> findAll();

	public List<HapOrgRole> findByPage(FlipInfo<HapOrgRole> fpi);
	
	public List<HapOrgRole> findByMap(Map param);
	
	public List<HapOrgRole> find(Map param);

}