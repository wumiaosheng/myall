package com.neo.hapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neo.hapi.UserContext;
import com.neo.hapi.dao.HapUserAccountDao;
import com.neo.hapi.dao.HapUserDao;
import com.neo.hapi.entity.HapMenu;
import com.neo.hapi.entity.HapOrg;
import com.neo.hapi.entity.HapOrgLink;
import com.neo.hapi.entity.HapUser;
import com.neo.hapi.entity.HapUserAccount;

@Service
public class LoginServiceImpl implements LoginService{
    
	@Autowired 
	private HapUserAccountDao hapUserAccountDao;
	@Autowired 
	private HapUserDao hapUserDao;
	
	@Autowired
	private HapMenuService hapMenuService;
	
	@Override
	public UserContext transLogin(HapUserAccount hua){
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String,String>();
		map.put("account", hua.getAccount());
		List<HapUserAccount> huas=hapUserAccountDao.find(map);
		if(huas!=null&&huas.size()>0){
			hua=huas.get(0);
			long userId=hua.getId();
			HapUser hapUser=hapUserDao.findById(userId);
			String password=hua.getPassword();
			//if(password.equals(hua.getPassword())){
				////获取当前登入人的菜单
				HapMenu hm=filtMenuByAccout(hua.getId());
				////当前登入人部门
				HapOrg currentHapOrg=null;
				////当前登入人最近一级公司
				HapOrg recentCompany=null;
				////当前登入人 所有下级部门
				List<Long> subOrgIds=null;
				UserContext uc=new UserContext();
				uc.setHapUser(hapUser);
				uc.setUserAccount(hua);
				uc.setMenu(hm);
				uc.setCurrentHapOrg(currentHapOrg);
				uc.setRecentCompany(recentCompany);
				uc.setSubOrgIds(subOrgIds);
				return uc;
			//}
			////密码输错
			//return null;
		}
		return null;
		//else{
			///throw new Exception("");
		//}
		////用户不存在
		///throw new Exception("");
		
		
	}
	
	private HapMenu filtMenuByAccout(long userId){
		
		List<HapMenu> hms=hapMenuService.findAll();
		HapMenu hm=buildList2Tree(hms);
		return hm;
	}
	
	private HapMenu buildList2Tree(List<HapMenu> menus){
		HapMenu hapmenu=new HapMenu();
		hapmenu.setMenuname("菜单树");
		hapmenu.setParentid(-1);
		hapmenu.setId(0);
		if(menus!=null&&menus.size()>0){
			for (HapMenu hm : menus) {
				if(hm.getParentid()==0){
					List<HapMenu> hms=hapmenu.getChildMenu();
					if(hms==null){
						hms=new ArrayList<HapMenu>();
						hapmenu.setChildMenu(hms);
					}
					hms.add(hm);
				}
				for(HapMenu hmTemp : menus) {
					if(hm.getId()==hmTemp.getParentid()){
						List<HapMenu> hms=hm.getChildMenu();
						if(hms==null){
							hms=new ArrayList<HapMenu>();
							hm.setChildMenu(hms);
						}
						hms.add(hmTemp);
					}
					
				}
			}
		}
		return hapmenu;
	}
}
