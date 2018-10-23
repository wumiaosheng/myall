package com.neo.hapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.dao.HapTemplateDao;
import com.neo.hapi.entity.HapTemplate;

@Service
public class HapTemplateServiceImpl implements HapTemplateService{
  
	@Autowired
	private HapTemplateDao hapTemplateDao;
	
	@Override
	public ReturnStatus update(HapTemplate hapTemplate) {
		// TODO Auto-generated method stub
		if(hapTemplate.getId()==0){
			hapTemplateDao.insert(hapTemplate);
		}else{
			hapTemplateDao.update(hapTemplate);
		}
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}

	@Override
	public ReturnStatus deleteById(long id) {
		// TODO Auto-generated method stub
		hapTemplateDao.delete(id);
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}

	@Override
	public HapTemplate findById(long id) {
		// TODO Auto-generated method stub
		return hapTemplateDao.findById(id);
	}

	@Override
	public List<HapTemplate> findAll() {
		// TODO Auto-generated method stub
		return hapTemplateDao.findAll();
	}

	@Override
	public FlipInfo<HapTemplate> findByPage(FlipInfo flp) {
		// TODO Auto-generated method stub
		 hapTemplateDao.findByPage(flp);
		 return flp;
	}

}
