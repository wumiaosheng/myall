package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapUserOrg;

public interface HapUserOrgDao {

	@Insert("insert into hap_user_org (ID, ORG_ID, USER_ID, CREATE_TIME, CREATE_USER_ID, UPDATE_USER_ID, UPDATE_TIME) values(#{id}, #{orgId}, #{userId}, #{createTime,jdbcType=TIMESTAMP}, #{createUserId}, #{updateUserId}, #{updateTime,jdbcType=TIMESTAMP})")
	public int insert(HapUserOrg hapUserOrg);
	
	@Delete("delete from hap_user_org where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_user_org set ID=#{id}, ORG_ID=#{orgId}, USER_ID=#{userId}, CREATE_TIME=#{createTime,jdbcType=TIMESTAMP}, CREATE_USER_ID=#{createUserId}, UPDATE_USER_ID=#{updateUserId}, UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP} where id=#{id}")
	public int update(HapUserOrg hapUserOrg);
	
	@Select("select * from hap_user_org where id = #{id}")
	public HapUserOrg findById(long id);

	@Select("select * from hap_user_org")
	public List<HapUserOrg> findAll();

	public List<HapUserOrg> findByPage(FlipInfo<HapUserOrg> fpi);
	
	public List<HapUserOrg> findByMap(Map param);
	
	public List<HapUserOrg> find(Map param);

}