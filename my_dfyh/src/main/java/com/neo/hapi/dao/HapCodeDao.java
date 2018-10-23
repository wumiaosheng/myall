package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapCode;

public interface HapCodeDao {

	@Insert("insert into hap_code (ID, CODEVALUE, CODETEXT, CODETYPE, CODEORDER, CREATE_TIME, CREATE_USER_ID, UPDATE_TIME, UPDATE_USER_ID) values(#{id}, #{codevalue,jdbcType=VARCHAR}, #{codetext,jdbcType=VARCHAR}, #{codetype,jdbcType=VARCHAR}, #{codeorder}, #{createTime,jdbcType=TIMESTAMP}, #{createUserId}, #{updateTime,jdbcType=TIMESTAMP}, #{updateUserId})")
	public int insert(HapCode hapCode);
	
	@Delete("delete from hap_code where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_code set ID=#{id}, CODEVALUE=#{codevalue,jdbcType=VARCHAR}, CODETEXT=#{codetext,jdbcType=VARCHAR}, CODETYPE=#{codetype,jdbcType=VARCHAR}, CODEORDER=#{codeorder}, CREATE_TIME=#{createTime,jdbcType=TIMESTAMP}, CREATE_USER_ID=#{createUserId}, UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP}, UPDATE_USER_ID=#{updateUserId} where id=#{id}")
	public int update(HapCode hapCode);
	
	@Select("select * from hap_code where id = #{id}")
	public HapCode findById(long id);

	@Select("select * from hap_code")
	public List<HapCode> findAll();

	public List<HapCode> findByPage(FlipInfo<HapCode> fpi);
	
	public List<HapCode> findByMap(Map param);
	
	public List<HapCode> find(Map param);

}