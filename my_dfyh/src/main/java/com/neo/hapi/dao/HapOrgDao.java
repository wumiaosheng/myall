package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapOrg;

public interface HapOrgDao {

	@Insert("insert into hap_org (ID, ORGNAME, ORGABBR, ORGCODE, LEVELCODE, ORGLEVEL, ORGORDER, CONTACT, DESCRIPTION, PARENTID, ORGTYPE, REGION, VERSION, ISDELETE, CREATE_TIME, CREATE_USER_ID, UPDATE_USER_ID, UPDATE_TIME, CREATEORGID, TEMP1, TEMP2) values(#{id}, #{orgname,jdbcType=VARCHAR}, #{orgabbr,jdbcType=VARCHAR}, #{orgcode,jdbcType=VARCHAR}, #{levelcode,jdbcType=VARCHAR}, #{orglevel}, #{orgorder}, #{contact,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, #{parentid}, #{orgtype,jdbcType=VARCHAR}, #{region,jdbcType=VARCHAR}, #{version,jdbcType=VARCHAR}, #{isdelete}, #{createTime,jdbcType=TIMESTAMP}, #{createUserId}, #{updateUserId}, #{updateTime,jdbcType=TIMESTAMP}, #{createorgid}, #{temp1,jdbcType=VARCHAR}, #{temp2,jdbcType=VARCHAR})")
	public int insert(HapOrg hapOrg);
	
	@Delete("delete from hap_org where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_org set ID=#{id}, ORGNAME=#{orgname,jdbcType=VARCHAR}, ORGABBR=#{orgabbr,jdbcType=VARCHAR}, ORGCODE=#{orgcode,jdbcType=VARCHAR}, LEVELCODE=#{levelcode,jdbcType=VARCHAR}, ORGLEVEL=#{orglevel}, ORGORDER=#{orgorder}, CONTACT=#{contact,jdbcType=VARCHAR}, DESCRIPTION=#{description,jdbcType=VARCHAR}, PARENTID=#{parentid}, ORGTYPE=#{orgtype,jdbcType=VARCHAR}, REGION=#{region,jdbcType=VARCHAR}, VERSION=#{version,jdbcType=VARCHAR}, ISDELETE=#{isdelete}, CREATE_TIME=#{createTime,jdbcType=TIMESTAMP}, CREATE_USER_ID=#{createUserId}, UPDATE_USER_ID=#{updateUserId}, UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP}, CREATEORGID=#{createorgid}, TEMP1=#{temp1,jdbcType=VARCHAR}, TEMP2=#{temp2,jdbcType=VARCHAR} where id=#{id}")
	public int update(HapOrg hapOrg);
	
	@Select("select * from hap_org where id = #{id}")
	public HapOrg findById(long id);

	@Select("select * from hap_org")
	public List<HapOrg> findAll();

	public List<HapOrg> findByPage(FlipInfo<HapOrg> fpi);
	
	public List<HapOrg> findByMap(Map param);
	
	public List<HapOrg> find(Map param);

}