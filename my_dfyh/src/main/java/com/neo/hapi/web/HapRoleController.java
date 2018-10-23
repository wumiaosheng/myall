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
import com.neo.hapi.entity.HapRole;
import com.neo.hapi.entity.HapScenario;
import com.neo.hapi.entity.HapSystem;
import com.neo.hapi.service.HapRoleService;

@RestController
@RequestMapping("/role")
public class HapRoleController {
	
	@Autowired
	private HapRoleService hapRoleService;
	
	@RequestMapping("/toQuery")
	public ModelAndView toQuery(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("role/query");
	    return mv;
	}
	
	@RequestMapping("/toTable")
	public ModelAndView toTable(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("role/listTable");
	    return mv;
	}
	
	@RequestMapping("/query")
	public FlipInfo<HapRole> query(HttpServletRequest request){
		FlipInfo<HapRole> flp=new FlipPageInfo(request);
		flp=hapRoleService.findByPage(flp);
	    return flp;
	}
	
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request,long id){
		  ModelAndView mv=new ModelAndView();
		  HapRole hr=hapRoleService.findById(id);
		  request.setAttribute("hapRole", hr);
		  return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(HapRole hr){
		  ModelAndView mv=new ModelAndView();
		  hapRoleService.update(hr);
		  mv.setViewName("role/upsert");
		  return mv;
	}
	
	@RequestMapping("/delete")
	public ReturnStatus delete(long  id){
		  ReturnStatus rs=hapRoleService.deleteById(id);
		  return rs;
	}

}
