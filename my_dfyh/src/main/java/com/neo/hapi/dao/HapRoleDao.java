package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapRole;

public interface HapRoleDao {

	@Insert("insert into hap_role (ID, ROLENAME, DESCRIPTION, EFFECT_START, EFFECT_END, ROLECODE, STATUS, CREATE_TIME, CREATE_USER_ID, UPDATE_TIME, UPDATE_USER_ID, SCENARIO_ID, CREATEORGID) values(#{id}, #{rolename,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, #{effectStart,jdbcType=TIMESTAMP}, #{effectEnd,jdbcType=TIMESTAMP}, #{rolecode,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{createUserId}, #{updateTime,jdbcType=TIMESTAMP}, #{updateUserId}, #{scenarioId}, #{createorgid})")
	public int insert(HapRole hapRole);
	
	@Delete("delete from hap_role where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_role set ID=#{id}, ROLENAME=#{rolename,jdbcType=VARCHAR}, DESCRIPTION=#{description,jdbcType=VARCHAR}, EFFECT_START=#{effectStart,jdbcType=TIMESTAMP}, EFFECT_END=#{effectEnd,jdbcType=TIMESTAMP}, ROLECODE=#{rolecode,jdbcType=VARCHAR}, STATUS=#{status,jdbcType=VARCHAR}, CREATE_TIME=#{createTime,jdbcType=TIMESTAMP}, CREATE_USER_ID=#{createUserId}, UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP}, UPDATE_USER_ID=#{updateUserId}, SCENARIO_ID=#{scenarioId}, CREATEORGID=#{createorgid} where id=#{id}")
	public int update(HapRole hapRole);
	
	@Select("select * from hap_role where id = #{id}")
	public HapRole findById(long id);

	@Select("select * from hap_role")
	public List<HapRole> findAll();

	public List<HapRole> findByPage(FlipInfo<HapRole> fpi);
	
	public List<HapRole> findByMap(Map param);
	
	public List<HapRole> find(Map param);

}