package com.neo.hapi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.framework.util.DateUtil;
import com.neo.framework.util.StringUtils;
import com.neo.hapi.dao.HapRoleDao;
import com.neo.hapi.dao.my.HapRoleMyDao;
import com.neo.hapi.entity.HapRole;

@Service
public class  HapRoleServiceImpl implements HapRoleService{

	@Autowired
	private HapRoleDao hapRoleDao;
	
	@Autowired
	private HapRoleMyDao hapRoleMyDao;
	
	@Override
	public List<HapRole> findRoleByUserId() {
		// TODO Auto-generated method stub
		Map<String, String> map=new HashMap<String, String>();
		return hapRoleDao.find(map);
	}

	@Override
	public ReturnStatus update(HapRole hr) {
		// TODO Auto-generated method stub
		if(hr.getId()==0){
			hapRoleDao.insert(hr);
		}else{
			hapRoleDao.update(hr);
		}
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}

	@Override
	public FlipInfo<HapRole> findByPage(FlipInfo<HapRole> fpi) {
		// TODO Auto-generated method stub
		List<HapRole> roles=hapRoleMyDao.findByPage(fpi);
		////处理有效期拼接
		for (HapRole role : roles) {
		    String effectTime=DateUtil.contactTime(role.getEffectStart(),role.getEffectEnd(),DateUtil.YEAR_MONTH_DAY_PATTERN,"至");
            role.setEffectTime(effectTime);
		}
		
		return fpi;
	}

	@Override
	public List<HapRole> findAll() {
		// TODO Auto-generated method stub
		return hapRoleDao.findAll();
	}

	@Override
	public HapRole findById(long id) {
		// TODO Auto-generated method stub
		return hapRoleDao.findById(id);
	}

	@Override
	public ReturnStatus deleteById(long id) {
		// TODO Auto-generated method stub
		hapRoleDao.delete(id);
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}
	
	


}
