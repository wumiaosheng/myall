package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapTemplate;

public interface HapTemplateDao {

	@Insert("insert into hap_template (ID, TEMPLATENAME, INDEX_URL, CREATE_TIME, CREATE_USER_ID, UPDATE_USER_ID, UPDATE_TIME, PICTURE_URL) values(#{id}, #{templatename,jdbcType=VARCHAR}, #{indexUrl,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{createUserId}, #{updateUserId}, #{updateTime,jdbcType=TIMESTAMP}, #{pictureUrl,jdbcType=VARCHAR})")
	public int insert(HapTemplate hapTemplate);
	
	@Delete("delete from hap_template where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_template set ID=#{id}, TEMPLATENAME=#{templatename,jdbcType=VARCHAR}, INDEX_URL=#{indexUrl,jdbcType=VARCHAR}, CREATE_TIME=#{createTime,jdbcType=TIMESTAMP}, CREATE_USER_ID=#{createUserId}, UPDATE_USER_ID=#{updateUserId}, UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP}, PICTURE_URL=#{pictureUrl,jdbcType=VARCHAR} where id=#{id}")
	public int update(HapTemplate hapTemplate);
	
	@Select("select * from hap_template where id = #{id}")
	public HapTemplate findById(long id);

	@Select("select * from hap_template")
	public List<HapTemplate> findAll();

	public List<HapTemplate> findByPage(FlipInfo<HapTemplate> fpi);
	
	public List<HapTemplate> findByMap(Map param);
	
	public List<HapTemplate> find(Map param);

}