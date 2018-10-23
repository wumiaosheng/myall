package com.neo.hapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.dao.HapScenarioDao;
import com.neo.hapi.dao.my.HapScenarioMyDao;
import com.neo.hapi.entity.HapScenario;
@Service
public class HapScenarioServiceImpl implements HapScenarioService{
    
	@Autowired
	private HapScenarioDao hapScenarioDao;
	@Autowired
	private HapScenarioMyDao hapScenarioMyDao;

	@Override
	public List<HapScenario> findAll() {
		// TODO Auto-generated method stub
		return hapScenarioDao.findAll();
	}

	@Override
	public HapScenario findById(long id) {
		// TODO Auto-generated method stub
		return hapScenarioDao.findById(id);
	}

	@Override
	public ReturnStatus update(HapScenario hapScenario) {
		// TODO Auto-generated method stub
		if(hapScenario.getId()==0){
			hapScenarioDao.insert(hapScenario);
		}else{
			hapScenarioDao.update(hapScenario);
		}
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}


	@Override
	public ReturnStatus deleteById(long id) {
		// TODO Auto-generated method stub
		hapScenarioDao.delete(id);
		
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}

	@Override
	public FlipInfo<HapScenario> findByPage(FlipInfo<HapScenario> flp) {
		// TODO Auto-generated method stub
		hapScenarioMyDao.findByPage(flp);
		 return flp;
	}
   
}
