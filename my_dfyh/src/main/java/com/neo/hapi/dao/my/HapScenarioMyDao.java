package com.neo.hapi.dao.my;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neo.common.entity.FlipInfo;
import com.neo.hapi.entity.HapScenario;

public interface HapScenarioMyDao {

	public List<HapScenario> findByPage(FlipInfo<HapScenario> fpi);

}