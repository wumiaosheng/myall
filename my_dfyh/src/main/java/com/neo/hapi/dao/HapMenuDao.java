package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapMenu;

public interface HapMenuDao {

	@Insert("insert into hap_menu (ID, PARENTID, SYSTEM_ID, MENUNAME, menuurl, FUNCCODE, MENULEVEL, LEVELCODE, MENUORDER, NAVURL, ICONURL, CREATE_TIME, CREATE_USER_ID, UPDATE_TIME, UPDATE_USER_ID) values(#{id}, #{parentid}, #{systemId}, #{menuname,jdbcType=VARCHAR}, #{menuurl,jdbcType=VARCHAR}, #{funccode,jdbcType=VARCHAR}, #{menulevel}, #{levelcode,jdbcType=VARCHAR}, #{menuorder}, #{navurl,jdbcType=VARCHAR}, #{iconurl,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{createUserId}, #{updateTime,jdbcType=TIMESTAMP}, #{updateUserId})")
	public int insert(HapMenu hapMenu);
	
	@Delete("delete from hap_menu where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_menu set ID=#{id}, PARENTID=#{parentid}, SYSTEM_ID=#{systemId}, MENUNAME=#{menuname,jdbcType=VARCHAR}, menuurl=#{menuurl,jdbcType=VARCHAR}, FUNCCODE=#{funccode,jdbcType=VARCHAR}, MENULEVEL=#{menulevel}, LEVELCODE=#{levelcode,jdbcType=VARCHAR}, MENUORDER=#{menuorder}, NAVURL=#{navurl,jdbcType=VARCHAR}, ICONURL=#{iconurl,jdbcType=VARCHAR}, CREATE_TIME=#{createTime,jdbcType=TIMESTAMP}, CREATE_USER_ID=#{createUserId}, UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP}, UPDATE_USER_ID=#{updateUserId} where id=#{id}")
	public int update(HapMenu hapMenu);
	
	@Select("select * from hap_menu where id = #{id}")
	public HapMenu findById(long id);

	@Select("select * from hap_menu")
	public List<HapMenu> findAll();

	public List<HapMenu> findByPage(FlipInfo<HapMenu> fpi);
	
	public List<HapMenu> findByMap(Map param);
	
	public List<HapMenu> find(Map param);

}