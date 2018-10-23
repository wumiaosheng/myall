package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapUserAccount;

public interface HapUserAccountDao {

	@Insert("insert into hap_user_account (ID, ENABLE, ACCOUNT, PASSWORD, STARTTIME, ENDTIME, ACCOUNT_TYPE, LASTLOGINDATE, LASTLOGINIP, PASSQUESTION, PASSANSWER, CREATE_TIME, CREATE_USER_ID, UPDATE_USER_ID, UPDATE_TIME, DELFLAG, CREATEORGID, MEMO, TEMP1, TEMP2) values(#{id}, #{enable,jdbcType=VARCHAR}, #{account,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, #{starttime,jdbcType=TIMESTAMP}, #{endtime,jdbcType=TIMESTAMP}, #{accountType,jdbcType=VARCHAR}, #{lastlogindate,jdbcType=TIMESTAMP}, #{lastloginip,jdbcType=VARCHAR}, #{passquestion,jdbcType=VARCHAR}, #{passanswer,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{createUserId}, #{updateUserId}, #{updateTime,jdbcType=TIMESTAMP}, #{delflag,jdbcType=VARCHAR}, #{createorgid}, #{memo,jdbcType=VARCHAR}, #{temp1,jdbcType=VARCHAR}, #{temp2,jdbcType=VARCHAR})")
	public int insert(HapUserAccount hapUserAccount);
	
	@Delete("delete from hap_user_account where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_user_account set ID=#{id}, ENABLE=#{enable,jdbcType=VARCHAR}, ACCOUNT=#{account,jdbcType=VARCHAR}, PASSWORD=#{password,jdbcType=VARCHAR}, STARTTIME=#{starttime,jdbcType=TIMESTAMP}, ENDTIME=#{endtime,jdbcType=TIMESTAMP}, ACCOUNT_TYPE=#{accountType,jdbcType=VARCHAR}, LASTLOGINDATE=#{lastlogindate,jdbcType=TIMESTAMP}, LASTLOGINIP=#{lastloginip,jdbcType=VARCHAR}, PASSQUESTION=#{passquestion,jdbcType=VARCHAR}, PASSANSWER=#{passanswer,jdbcType=VARCHAR}, CREATE_TIME=#{createTime,jdbcType=TIMESTAMP}, CREATE_USER_ID=#{createUserId}, UPDATE_USER_ID=#{updateUserId}, UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP}, DELFLAG=#{delflag,jdbcType=VARCHAR}, CREATEORGID=#{createorgid}, MEMO=#{memo,jdbcType=VARCHAR}, TEMP1=#{temp1,jdbcType=VARCHAR}, TEMP2=#{temp2,jdbcType=VARCHAR} where id=#{id}")
	public int update(HapUserAccount hapUserAccount);
	
	@Select("select * from hap_user_account where id = #{id}")
	public HapUserAccount findById(long id);

	@Select("select * from hap_user_account")
	public List<HapUserAccount> findAll();

	public List<HapUserAccount> findByPage(FlipInfo<HapUserAccount> fpi);
	
	public List<HapUserAccount> findByMap(Map param);
	
	public List<HapUserAccount> find(Map param);

}