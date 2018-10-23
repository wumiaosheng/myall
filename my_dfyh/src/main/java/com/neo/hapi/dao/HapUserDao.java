package com.neo.hapi.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapUser;

public interface HapUserDao {

	@Insert("insert into hap_user (ID, USERNAME, OFFICE_FAX, OFFICE_PHONE, PHONE, EMAIL, AUTHCODE, AUTHCODETIME, EQUIPMENTID, ADDRESS, ISDELETE, CREATE_TIME, CREATE_USER_ID, UPDATE_TIME, UPDATE_USER_ID, USER_TYPE, IDCARD, CLIENT_IDS_JSON, TEAM_ID, AVATAR, SEX, AGE, WORK_AGE, WORKER_TYPE, IDCARD_FRONT, IDCARD_BACK, CERTS, WORK_HIS, CARD_NUM, WORK_CARD_NUM, HAS_INSURANCE, IS_LEADER, IDCARD_NATION, IDCARD_ADDRESS, IDCARD_BIRTHDAY, IDCARD_SIGN_DATE, IDCARD_EXPIRE_DATE, IDCARD_SIGN_ORG, WORK_CARD_JSON, VALID, PRIZE, BAD_RECORD) values(#{id}, #{username,jdbcType=VARCHAR}, #{officeFax,jdbcType=VARCHAR}, #{officePhone,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{authcode,jdbcType=VARCHAR}, #{authcodetime,jdbcType=TIMESTAMP}, #{equipmentid,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, #{isdelete}, #{createTime,jdbcType=TIMESTAMP}, #{createUserId}, #{updateTime,jdbcType=TIMESTAMP}, #{updateUserId}, #{userType,jdbcType=VARCHAR}, #{idcard,jdbcType=VARCHAR}, #{clientIdsJson,jdbcType=VARCHAR}, #{teamId}, #{avatar,jdbcType=VARCHAR}, #{sex,jdbcType=VARCHAR}, #{age}, #{workAge}, #{workerType,jdbcType=VARCHAR}, #{idcardFront,jdbcType=VARCHAR}, #{idcardBack,jdbcType=VARCHAR}, #{certs,jdbcType=VARCHAR}, #{workHis,jdbcType=VARCHAR}, #{cardNum,jdbcType=VARCHAR}, #{workCardNum,jdbcType=VARCHAR}, #{hasInsurance,jdbcType=VARCHAR}, #{isLeader,jdbcType=VARCHAR}, #{idcardNation,jdbcType=VARCHAR}, #{idcardAddress,jdbcType=VARCHAR}, #{idcardBirthday,jdbcType=TIMESTAMP}, #{idcardSignDate,jdbcType=TIMESTAMP}, #{idcardExpireDate,jdbcType=TIMESTAMP}, #{idcardSignOrg,jdbcType=VARCHAR}, #{workCardJson,jdbcType=VARCHAR}, #{valid,jdbcType=VARCHAR}, #{prize,jdbcType=VARCHAR}, #{badRecord,jdbcType=VARCHAR})")
	public int insert(HapUser hapUser);
	
	@Delete("delete from hap_user where id=#{id}")
	public int delete(long id);
	
	@Update("update hap_user set ID=#{id}, USERNAME=#{username,jdbcType=VARCHAR}, OFFICE_FAX=#{officeFax,jdbcType=VARCHAR}, OFFICE_PHONE=#{officePhone,jdbcType=VARCHAR}, PHONE=#{phone,jdbcType=VARCHAR}, EMAIL=#{email,jdbcType=VARCHAR}, AUTHCODE=#{authcode,jdbcType=VARCHAR}, AUTHCODETIME=#{authcodetime,jdbcType=TIMESTAMP}, EQUIPMENTID=#{equipmentid,jdbcType=VARCHAR}, ADDRESS=#{address,jdbcType=VARCHAR}, ISDELETE=#{isdelete}, CREATE_TIME=#{createTime,jdbcType=TIMESTAMP}, CREATE_USER_ID=#{createUserId}, UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP}, UPDATE_USER_ID=#{updateUserId}, USER_TYPE=#{userType,jdbcType=VARCHAR}, IDCARD=#{idcard,jdbcType=VARCHAR}, CLIENT_IDS_JSON=#{clientIdsJson,jdbcType=VARCHAR}, TEAM_ID=#{teamId}, AVATAR=#{avatar,jdbcType=VARCHAR}, SEX=#{sex,jdbcType=VARCHAR}, AGE=#{age}, WORK_AGE=#{workAge}, WORKER_TYPE=#{workerType,jdbcType=VARCHAR}, IDCARD_FRONT=#{idcardFront,jdbcType=VARCHAR}, IDCARD_BACK=#{idcardBack,jdbcType=VARCHAR}, CERTS=#{certs,jdbcType=VARCHAR}, WORK_HIS=#{workHis,jdbcType=VARCHAR}, CARD_NUM=#{cardNum,jdbcType=VARCHAR}, WORK_CARD_NUM=#{workCardNum,jdbcType=VARCHAR}, HAS_INSURANCE=#{hasInsurance,jdbcType=VARCHAR}, IS_LEADER=#{isLeader,jdbcType=VARCHAR}, IDCARD_NATION=#{idcardNation,jdbcType=VARCHAR}, IDCARD_ADDRESS=#{idcardAddress,jdbcType=VARCHAR}, IDCARD_BIRTHDAY=#{idcardBirthday,jdbcType=TIMESTAMP}, IDCARD_SIGN_DATE=#{idcardSignDate,jdbcType=TIMESTAMP}, IDCARD_EXPIRE_DATE=#{idcardExpireDate,jdbcType=TIMESTAMP}, IDCARD_SIGN_ORG=#{idcardSignOrg,jdbcType=VARCHAR}, WORK_CARD_JSON=#{workCardJson,jdbcType=VARCHAR}, VALID=#{valid,jdbcType=VARCHAR}, PRIZE=#{prize,jdbcType=VARCHAR}, BAD_RECORD=#{badRecord,jdbcType=VARCHAR} where id=#{id}")
	public int update(HapUser hapUser);
	
	@Select("select * from hap_user where id = #{id}")
	public HapUser findById(long id);

	@Select("select * from hap_user")
	public List<HapUser> findAll();

	public List<HapUser> findByPage(FlipInfo<HapUser> fpi);
	
	public List<HapUser> findByMap(Map param);
	
	public List<HapUser> find(Map param);

}