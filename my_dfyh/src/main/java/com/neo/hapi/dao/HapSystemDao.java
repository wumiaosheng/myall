package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapSystem;

public interface HapSystemDao {

	@Insert("insert into hap_system (ID, SYSNAME, DESCRIPTION, ROOTURI, CREATE_TIME, CREATE_USER_ID, UPDATE_TIME, UPDATE_USER_ID) values(#{id}, #{sysname,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, #{rooturi,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{createUserId}, #{updateTime,jdbcType=TIMESTAMP}, #{updateUserId})")
	public int insert(HapSystem hapSystem);
	
	@Delete("delete from hap_system where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_system set ID=#{id}, SYSNAME=#{sysname,jdbcType=VARCHAR}, DESCRIPTION=#{description,jdbcType=VARCHAR}, ROOTURI=#{rooturi,jdbcType=VARCHAR}, CREATE_TIME=#{createTime,jdbcType=TIMESTAMP}, CREATE_USER_ID=#{createUserId}, UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP}, UPDATE_USER_ID=#{updateUserId} where id=#{id}")
	public int update(HapSystem hapSystem);
	
	@Select("select * from hap_system where id = #{id}")
	public HapSystem findById(long id);

	@Select("select * from hap_system")
	public List<HapSystem> findAll();

	public List<HapSystem> findByPage(FlipInfo<HapSystem> fpi);
	
	public List<HapSystem> findByMap(Map param);
	
	public List<HapSystem> find(Map param);

}