package com.neo.hapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.UserContext;
import com.neo.hapi.dao.HapUserAccountDao;
import com.neo.hapi.dao.HapUserDao;
import com.neo.hapi.entity.HapMenu;
import com.neo.hapi.entity.HapOrg;
import com.neo.hapi.entity.HapOrgLink;
import com.neo.hapi.entity.HapUser;
import com.neo.hapi.entity.HapUserAccount;

@Service
public class HapAccountServiceImpl implements HapAccountService{
  
	@Autowired
	private HapUserAccountDao hapUserAccountDao;
	@Override
	public ReturnStatus update(HapUserAccount hapUserAccount) {
		// TODO Auto-generated method stub
		if(hapUserAccount.getId()==0){
			hapUserAccountDao.insert(hapUserAccount);
		}else{
			hapUserAccountDao.update(hapUserAccount);
		}
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}

	@Override
	public ReturnStatus deleteById(long id) {
		// TODO Auto-generated method stub
		hapUserAccountDao.delete(id);
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}

	@Override
	public HapUserAccount findById(long id) {
		// TODO Auto-generated method stub
		return hapUserAccountDao.findById(id);
	}

	@Override
	public List<HapUserAccount> findAll() {
		// TODO Auto-generated method stub
		return hapUserAccountDao.findAll();
	}

	@Override
	public FlipInfo<HapUserAccount> findByPage(FlipInfo<HapUserAccount> flp) {
		// TODO Auto-generated method stub
		 hapUserAccountDao.findByPage(flp);
		 return flp;
	}
    
	
}
