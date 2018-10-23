package com.neo.hapi.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.FlipPageInfo;
import com.neo.common.entity.MyBatisEntity;
import com.neo.common.entity.ReturnStatus;
import com.neo.common.entity.TreeNode;
import com.neo.hapi.entity.HapMenu;
import com.neo.hapi.service.HapMenuService;

@RestController
@RequestMapping("/menu")
public class HapMenuController {
	
	@Autowired
	private HapMenuService hapMenuService;
	
	@RequestMapping("/toQuery")
	public ModelAndView toQuery(HttpServletRequest request){
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("menu/query");
	    return mv;
	}
	
	@RequestMapping("/toTable")
	public ModelAndView toTable(HttpServletRequest request){
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("menu/listTable");
	    return mv;
	}
	
	
	@RequestMapping("/query")
	public FlipInfo<HapMenu> query(HttpServletRequest request){
		FlipInfo<HapMenu> flp=new FlipPageInfo(request);
		flp=hapMenuService.findByPage(flp);
	    return flp;
	}
	
	@RequestMapping("/getMenuTree")
	public List<HapMenu> getMenuTree(){
		List<HapMenu> dataList = hapMenuService.findAll2Tree();
		// 添加根节点
		HapMenu root = new HapMenu();
		root.setId(0);
		root.setParentid(-1);
		root.setMenuname("菜单树");
		dataList.add(root);
	    return dataList;
	}
	
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request,long id){
		  ModelAndView mv=new ModelAndView();
		  HapMenu hm=hapMenuService.findById(id);
		  request.setAttribute("hapMenu", hm);
		  return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(HapMenu hm){
		  ModelAndView mv=new ModelAndView();
		  hapMenuService.update(hm);
		  mv.setViewName("menu/upsert");
		  return mv;
	}
	
	@RequestMapping("/delete")
	public ReturnStatus delete(long  id){
		  ReturnStatus rs=hapMenuService.deleteById(id);
		  return rs;
	}

}
