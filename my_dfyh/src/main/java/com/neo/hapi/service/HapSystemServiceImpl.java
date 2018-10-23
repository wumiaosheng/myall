package com.neo.hapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.dao.HapSystemDao;
import com.neo.hapi.entity.HapSystem;
@Service
public class HapSystemServiceImpl implements HapSystemService{
    
	@Autowired
	private HapSystemDao hapSystemDao;
	

	@Override
	public List<HapSystem> findAll() {
		// TODO Auto-generated method stub
		return hapSystemDao.findAll();
	}

	@Override
	public HapSystem findById(long id) {
		// TODO Auto-generated method stub
		return hapSystemDao.findById(id);
	}

	@Override
	public ReturnStatus update(HapSystem hapSystem) {
		// TODO Auto-generated method stub
		if(hapSystem.getId()==0){
			hapSystemDao.insert(hapSystem);
		}else{
			hapSystemDao.update(hapSystem);
		}
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}

	@Override
	public FlipInfo<HapSystem> findByPage(FlipInfo<HapSystem> fpi) {
		// TODO Auto-generated method stub
		 hapSystemDao.findByPage(fpi);
		 return fpi;
	}

	@Override
	public ReturnStatus deleteById(long id) {
		// TODO Auto-generated method stub
		hapSystemDao.delete(id);
		
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}
   
}
