package com.neo.hapi.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.FlipPageInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.entity.HapSystem;
import com.neo.hapi.entity.HapUser;
import com.neo.hapi.service.HapUserService;

@RestController
@RequestMapping("/user")
public class HapUserController {
   
	@Autowired
	private HapUserService hapUserService;
	
	@RequestMapping("/toQuery")
	public ModelAndView toQuery(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("user/query");
	    return mv;
	}
	
	@RequestMapping("/toTable")
	public ModelAndView toTable(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("user/listTable");
	    return mv;
	}
	
	@RequestMapping("/query")
	public FlipInfo<HapUser> query(HttpServletRequest request){
		FlipInfo<HapUser> flp=new FlipPageInfo(request);
		flp=hapUserService.findByPage(flp);
	    return flp;
	}
	
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request,long id){
		  ModelAndView mv=new ModelAndView();
		  HapUser hu=hapUserService.findById(id);
		  request.setAttribute("hapUser", hu);
		  return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(HapUser hu){
		  ModelAndView mv=new ModelAndView();
		  hapUserService.update(hu);
		  mv.setViewName("user/upsert");
		  return mv;
	}
	
	@RequestMapping("/delete")
	public ReturnStatus delete(long  id){
		  ReturnStatus rs=hapUserService.deleteById(id);
		  return rs;
	}
	
}
