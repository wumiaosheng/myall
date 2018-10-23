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
import com.neo.hapi.service.HapSystemService;

@RestController
@RequestMapping("/sys")
public class HapSystemController {
	
	@Autowired
	private HapSystemService hapSystemService;
	
	@RequestMapping("/toQuery")
	public ModelAndView toQuery(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("sys/query");
	    return mv;
	}
	
	
	@RequestMapping("/query")
	public FlipInfo<HapSystem> query(HttpServletRequest request){
		FlipInfo<HapSystem> flp=new FlipPageInfo(request);
		flp=hapSystemService.findByPage(flp);
	    return flp;
	}
	
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request,long id){
		  ModelAndView mv=new ModelAndView();
		  HapSystem hs=hapSystemService.findById(id);
		  request.setAttribute("hapSystem", hs);
		  return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(HapSystem hs){
		  ModelAndView mv=new ModelAndView();
		  hapSystemService.update(hs);
		  mv.setViewName("sys/upsert");
		  return mv;
	}
	
	@RequestMapping("/delete")
	public ReturnStatus delete(long  id){
		  ReturnStatus rs=hapSystemService.deleteById(id);
		  return rs;
	}

}
