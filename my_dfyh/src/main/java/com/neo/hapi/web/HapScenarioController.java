package com.neo.hapi.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.entity.FlipInfo;
import com.neo.common.entity.FlipPageInfo;
import com.neo.common.entity.ReturnStatus;
import com.neo.hapi.entity.HapScenario;
import com.neo.hapi.entity.HapTemplate;
import com.neo.hapi.service.HapScenarioService;

@RestController
@RequestMapping("/scenario")
public class HapScenarioController {
	
	@Autowired
	private HapScenarioService hapScenarioService;
	
	@RequestMapping("/toQuery")
	public ModelAndView toQuery(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("scenario/query");
	    return mv;
	}
	
	@RequestMapping("/query")
	public FlipInfo<HapScenario> query(HttpServletRequest request){
		FlipInfo<HapScenario> flp=new FlipPageInfo(request);
		flp=hapScenarioService.findByPage(flp);
	    return flp;
	}
	
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request,long id){
		  ModelAndView mv=new ModelAndView();
		  HapScenario hs=hapScenarioService.findById(id);
		  request.setAttribute("scenario", hs);
		  return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(HapScenario hs){
		  ModelAndView mv=new ModelAndView();
		  hapScenarioService.update(hs);
		  mv.setViewName("scenario/upsert");
		  return mv;
	}
	
	@RequestMapping("/delete")
	public ReturnStatus delete(long  id){
		  ReturnStatus rs=hapScenarioService.deleteById(id);
		  return rs;
	}

}
