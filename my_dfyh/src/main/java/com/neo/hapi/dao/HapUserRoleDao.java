package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapUserRole;

public interface HapUserRoleDao {

	@Insert("insert into hap_user_role (ID, ROLE_ID, USER_ID, CREATE_TIME, CREATE_USER_ID, UPDATE_USER_ID, UPDATE_TIME, CREATEORGID) values(#{id}, #{roleId}, #{userId}, #{createTime,jdbcType=TIMESTAMP}, #{createUserId}, #{updateUserId}, #{updateTime,jdbcType=TIMESTAMP}, #{createorgid})")
	public int insert(HapUserRole hapUserRole);
	
	@Delete("delete from hap_user_role where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_user_role set ID=#{id}, ROLE_ID=#{roleId}, USER_ID=#{userId}, CREATE_TIME=#{createTime,jdbcType=TIMESTAMP}, CREATE_USER_ID=#{createUserId}, UPDATE_USER_ID=#{updateUserId}, UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP}, CREATEORGID=#{createorgid} where id=#{id}")
	public int update(HapUserRole hapUserRole);
	
	@Select("select * from hap_user_role where id = #{id}")
	public HapUserRole findById(long id);

	@Select("select * from hap_user_role")
	public List<HapUserRole> findAll();

	public List<HapUserRole> findByPage(FlipInfo<HapUserRole> fpi);
	
	public List<HapUserRole> findByMap(Map param);
	
	public List<HapUserRole> find(Map param);

}