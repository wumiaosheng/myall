package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapFunction;

public interface HapFunctionDao {

	@Insert("insert into hap_function (ID, FUNCNAME, NAVURL, FUNCCODE, FUNCORDER, SYSTEM_ID, CREATE_TIME, CREATE_USER_ID, UPDATE_TIME, UPDATE_USER_ID) values(#{id}, #{funcname,jdbcType=VARCHAR}, #{navurl,jdbcType=VARCHAR}, #{funccode,jdbcType=VARCHAR}, #{funcorder}, #{systemId}, #{createTime,jdbcType=TIMESTAMP}, #{createUserId}, #{updateTime,jdbcType=TIMESTAMP}, #{updateUserId})")
	public int insert(HapFunction hapFunction);
	
	@Delete("delete from hap_function where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_function set ID=#{id}, FUNCNAME=#{funcname,jdbcType=VARCHAR}, NAVURL=#{navurl,jdbcType=VARCHAR}, FUNCCODE=#{funccode,jdbcType=VARCHAR}, FUNCORDER=#{funcorder}, SYSTEM_ID=#{systemId}, CREATE_TIME=#{createTime,jdbcType=TIMESTAMP}, CREATE_USER_ID=#{createUserId}, UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP}, UPDATE_USER_ID=#{updateUserId} where id=#{id}")
	public int update(HapFunction hapFunction);
	
	@Select("select * from hap_function where id = #{id}")
	public HapFunction findById(long id);

	@Select("select * from hap_function")
	public List<HapFunction> findAll();

	public List<HapFunction> findByPage(FlipInfo<HapFunction> fpi);
	
	public List<HapFunction> findByMap(Map param);
	
	public List<HapFunction> find(Map param);

}