package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapScenario;

public interface HapScenarioDao {

	@Insert("insert into hap_scenario (ID, SCENENAME, TEMPATE_ID, DESCRIPTION, CREATE_TIME, CREATE_USER_ID, UPDATE_USER_ID, UPDATE_TIME) values(#{id}, #{scenename,jdbcType=VARCHAR}, #{tempateId}, #{description,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{createUserId}, #{updateUserId}, #{updateTime,jdbcType=TIMESTAMP})")
	public int insert(HapScenario hapScenario);
	
	@Delete("delete from hap_scenario where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_scenario set ID=#{id}, SCENENAME=#{scenename,jdbcType=VARCHAR}, TEMPATE_ID=#{tempateId}, DESCRIPTION=#{description,jdbcType=VARCHAR}, CREATE_TIME=#{createTime,jdbcType=TIMESTAMP}, CREATE_USER_ID=#{createUserId}, UPDATE_USER_ID=#{updateUserId}, UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP} where id=#{id}")
	public int update(HapScenario hapScenario);
	
	@Select("select * from hap_scenario where id = #{id}")
	public HapScenario findById(long id);

	@Select("select * from hap_scenario")
	public List<HapScenario> findAll();

	public List<HapScenario> findByPage(FlipInfo<HapScenario> fpi);
	
	public List<HapScenario> findByMap(Map param);
	
	public List<HapScenario> find(Map param);
}