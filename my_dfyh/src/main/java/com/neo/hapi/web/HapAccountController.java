package com.neo.hapi.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.FlipPageInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.entity.HapUserAccount;
import com.neo.hapi.service.HapAccountService;

@RestController
@RequestMapping("/account")
public class HapAccountController {
   
	@Autowired
	private HapAccountService hapAccountService;
	
	@RequestMapping("/toQuery")
	public ModelAndView toQuery(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("account/query");
	    return mv;
	}
	
	@RequestMapping("/toTable")
	public ModelAndView toTable(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("account/listTable");
	    return mv;
	}
	
	@RequestMapping("/query")
	public FlipInfo<HapUserAccount> query(HttpServletRequest request){
		FlipInfo<HapUserAccount> flp=new FlipPageInfo(request);
		flp=hapAccountService.findByPage(flp);
	    return flp;
	}
	
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request,long id){
		  ModelAndView mv=new ModelAndView();
		  HapUserAccount hua=hapAccountService.findById(id);
		  request.setAttribute("account", hua);
		  return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(HapUserAccount hua){
		  ModelAndView mv=new ModelAndView();
		  hapAccountService.update(hua);
		  mv.setViewName("account/upsert");
		  return mv;
	}
	
	@RequestMapping("/delete")
	public ReturnStatus delete(long  id){
		  ReturnStatus rs=hapAccountService.deleteById(id);
		  return rs;
	}
	
}
