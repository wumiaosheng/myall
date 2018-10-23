package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapRoleMenu;

public interface HapRoleMenuDao {

	@Insert("insert into hap_role_menu (ID, ROLE_ID, MENU_ID) values(#{id}, #{roleId}, #{menuId})")
	public int insert(HapRoleMenu hapRoleMenu);
	
	@Delete("delete from hap_role_menu where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_role_menu set ID=#{id}, ROLE_ID=#{roleId}, MENU_ID=#{menuId} where id=#{id}")
	public int update(HapRoleMenu hapRoleMenu);
	
	@Select("select * from hap_role_menu where id = #{id}")
	public HapRoleMenu findById(long id);

	@Select("select * from hap_role_menu")
	public List<HapRoleMenu> findAll();

	public List<HapRoleMenu> findByPage(FlipInfo<HapRoleMenu> fpi);
	
	public List<HapRoleMenu> findByMap(Map param);
	
	public List<HapRoleMenu> find(Map param);

}