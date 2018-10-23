package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapScenarioMenu;

public interface HapScenarioMenuDao {

	@Insert("insert into hap_scenario_menu (ID, MENU_ID, SCENARIO_ID, CREATE_TIME, CREATE_USER_ID, UPDATE_USER_ID, UPDATE_TIME) values(#{id}, #{menuId}, #{scenarioId}, #{createTime,jdbcType=TIMESTAMP}, #{createUserId}, #{updateUserId}, #{updateTime,jdbcType=TIMESTAMP})")
	public int insert(HapScenarioMenu hapScenarioMenu);
	
	@Delete("delete from hap_scenario_menu where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_scenario_menu set ID=#{id}, MENU_ID=#{menuId}, SCENARIO_ID=#{scenarioId}, CREATE_TIME=#{createTime,jdbcType=TIMESTAMP}, CREATE_USER_ID=#{createUserId}, UPDATE_USER_ID=#{updateUserId}, UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP} where id=#{id}")
	public int update(HapScenarioMenu hapScenarioMenu);
	
	@Select("select * from hap_scenario_menu where id = #{id}")
	public HapScenarioMenu findById(long id);

	@Select("select * from hap_scenario_menu")
	public List<HapScenarioMenu> findAll();

	public List<HapScenarioMenu> findByPage(FlipInfo<HapScenarioMenu> fpi);
	
	public List<HapScenarioMenu> findByMap(Map param);
	
	public List<HapScenarioMenu> find(Map param);

}