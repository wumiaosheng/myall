package com.neo.hapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.MyBatisEntity;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.dao.HapMenuDao;
import com.neo.hapi.entity.HapMenu;

@Service
public class HapMenuServiceImpl implements HapMenuService{
	
    @Autowired
    private HapMenuDao hapMenuDao;
    
	@Override
	public ReturnStatus update(HapMenu hapMenu) {
		// TODO Auto-generated method stub
		if(hapMenu.getId()==0){
			hapMenuDao.insert(hapMenu);
		}else{
			hapMenuDao.update(hapMenu);
		}
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}

	@Override
	public ReturnStatus deleteById(long id) {
		// TODO Auto-generated method stub
		hapMenuDao.delete(id);
		ReturnStatus rs=new ReturnStatus(true);
		return rs;
	}

	@Override
	public HapMenu findById(long id) {
		// TODO Auto-generated method stub
		return hapMenuDao.findById(id);
	}

	@Override
	public List<HapMenu> findAll() {
		// TODO Auto-generated method stub
		return hapMenuDao.findAll();
	}

	@Override
	public FlipInfo<HapMenu> findByPage(FlipInfo<HapMenu> flp) {
		// TODO Auto-generated method stub
		hapMenuDao.findByPage(flp);
		return flp;
	}

	@Override
	public List<HapMenu> findAll2Tree() {
		// TODO Auto-generated method stub
		return hapMenuDao.findAll();
	}
	

}
