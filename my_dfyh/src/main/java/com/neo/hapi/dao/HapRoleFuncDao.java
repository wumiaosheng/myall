package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapRoleFunc;

public interface HapRoleFuncDao {

	@Insert("insert into hap_role_func (ID, FUNC_ID, ROLE_ID, CREATE_TIME, CREATE_USER_ID, UPDATE_TIME, UPDATE_USER_ID) values(#{id}, #{funcId}, #{roleId}, #{createTime,jdbcType=TIMESTAMP}, #{createUserId}, #{updateTime,jdbcType=TIMESTAMP}, #{updateUserId})")
	public int insert(HapRoleFunc hapRoleFunc);
	
	@Delete("delete from hap_role_func where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_role_func set ID=#{id}, FUNC_ID=#{funcId}, ROLE_ID=#{roleId}, CREATE_TIME=#{createTime,jdbcType=TIMESTAMP}, CREATE_USER_ID=#{createUserId}, UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP}, UPDATE_USER_ID=#{updateUserId} where id=#{id}")
	public int update(HapRoleFunc hapRoleFunc);
	
	@Select("select * from hap_role_func where id = #{id}")
	public HapRoleFunc findById(long id);

	@Select("select * from hap_role_func")
	public List<HapRoleFunc> findAll();

	public List<HapRoleFunc> findByPage(FlipInfo<HapRoleFunc> fpi);
	
	public List<HapRoleFunc> findByMap(Map param);
	
	public List<HapRoleFunc> find(Map param);

}