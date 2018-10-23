package com.neo.hapi.service;

import java.util.List;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.dao.HapOrgDao;
import com.neo.hapi.entity.HapOrg;

@Service
public class HapOrgServiceImpl implements HapOrgService{
    
	@Autowired
	private HapOrgDao hapOrgDao;
	
	@Override
	public ReturnStatus update(HapOrg hapOrg) {
		// TODO Auto-generated method stub
		if(hapOrg.getId()==0){
			hapOrgDao.insert(hapOrg);
		}else{
			hapOrgDao.update(hapOrg);
		}
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}

	@Override
	public ReturnStatus deleteById(long id) {
		// TODO Auto-generated method stub
		hapOrgDao.delete(id);
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}

	@Override
	public HapOrg findById(long id) {
		// TODO Auto-generated method stub
		return hapOrgDao.findById(id);
	}

	@Override
	public List<HapOrg> findAll() {
		// TODO Auto-generated method stub
		return hapOrgDao.findAll();
	}

	@Override
	public FlipInfo<HapOrg> findByPage(FlipInfo<HapOrg> flp) {
		// TODO Auto-generated method stub
		 hapOrgDao.findByPage(flp);
		 return flp;
	}

	@Override
	public List<HapOrg> findAll2Tree() {
		// TODO Auto-generated method stub
		return hapOrgDao.findAll();
	}

}
