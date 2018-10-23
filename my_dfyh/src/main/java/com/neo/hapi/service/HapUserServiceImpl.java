package com.neo.hapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.dao.HapUserDao;
import com.neo.hapi.entity.HapUser;

@Service
public class HapUserServiceImpl implements HapUserService{
    
	@Autowired
	private HapUserDao  hapUserDao;
	
	@Override
	public ReturnStatus update(HapUser hapUser) {
		// TODO Auto-generated method stub
		if(hapUser.getId()==0){
			hapUserDao.update(hapUser);
		}else{
			hapUserDao.insert(hapUser);
		}
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}

	@Override
	public ReturnStatus deleteById(long id) {
		// TODO Auto-generated method stub
		hapUserDao.delete(id);
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}

	@Override
	public HapUser findById(long id) {
		// TODO Auto-generated method stub
		return hapUserDao.findById(id);
	}

	@Override
	public List<HapUser> findAll() {
		// TODO Auto-generated method stub
		return hapUserDao.findAll();
	}

	@Override
	public FlipInfo<HapUser> findByPage(FlipInfo<HapUser> flp) {
		// TODO Auto-generated method stub
		 hapUserDao.findByPage(flp);
		 return flp;
	}

}
