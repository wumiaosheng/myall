package com.neo.hapi.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.FlipPageInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.entity.HapMenu;
import com.neo.hapi.entity.HapOrg;
import com.neo.hapi.service.HapOrgService;

@RestController
@RequestMapping("/org")
public class HapOrgController {
   
	@Autowired
	private HapOrgService hapOrgService;
	
	@RequestMapping("/toQuery")
	public ModelAndView toQuery(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("org/query");
	    return mv;
	}
	
	@RequestMapping("/toTable")
	public ModelAndView toTable(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("org/listTable");
	    return mv;
	}
	
	@RequestMapping("/query")
	public FlipInfo<HapOrg> query(HttpServletRequest request){
		FlipInfo<HapOrg> flp=new FlipPageInfo(request);
		flp=hapOrgService.findByPage(flp);
	    return flp;
	}
	
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request,long id){
		  ModelAndView mv=new ModelAndView();
		  HapOrg ho=hapOrgService.findById(id);
		  request.setAttribute("hapOrg", ho);
		  return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(HapOrg ho){
		  ModelAndView mv=new ModelAndView();
		  hapOrgService.update(ho);
		  mv.setViewName("org/upsert");
		  return mv;
	}
	
	@RequestMapping("/delete")
	public ReturnStatus delete(long  id){
		  ReturnStatus rs=hapOrgService.deleteById(id);
		  return rs;
	}
	
	@RequestMapping("/getOrgTree")
	public List<HapOrg> getMenuTree(){
		List<HapOrg> dataList = hapOrgService.findAll2Tree();
		// 添加根节点
		HapOrg root = new HapOrg();
		root.setId(0);
		root.setParentid(-1);
		root.setOrgname("组织机构树");
		dataList.add(root);
	    return dataList;
	}
	
	
	
}
