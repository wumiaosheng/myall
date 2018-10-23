package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapOrgLink;

public interface HapOrgLinkDao {

	@Insert("insert into hap_org_link (ID, PARENT, CHILD) values(#{id}, #{parent}, #{child})")
	public int insert(HapOrgLink hapOrgLink);
	
	@Delete("delete from hap_org_link where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_org_link set ID=#{id}, PARENT=#{parent}, CHILD=#{child} where id=#{id}")
	public int update(HapOrgLink hapOrgLink);
	
	@Select("select * from hap_org_link where id = #{id}")
	public HapOrgLink findById(long id);

	@Select("select * from hap_org_link")
	public List<HapOrgLink> findAll();

	public List<HapOrgLink> findByPage(FlipInfo<HapOrgLink> fpi);
	
	public List<HapOrgLink> findByMap(Map param);
	
	public List<HapOrgLink> find(Map param);

}