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
import com.neo.hapi.entity.HapSystem;
import com.neo.hapi.entity.HapTemplate;
import com.neo.hapi.entity.HapUser;
import com.neo.hapi.service.HapRoleService;
import com.neo.hapi.service.HapTemplateService;

@RestController
@RequestMapping("/templates")
public class HapTemplatesController {
	
	@Autowired
	private HapTemplateService hapTemplateService;
	
	@RequestMapping("/toQuery")
	public ModelAndView toQuery(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("templates/query");
	    return mv;
	}
	
	@RequestMapping("/query")
	public FlipInfo<HapTemplate> query(HttpServletRequest request){
		FlipInfo<HapTemplate> flp=new FlipPageInfo(request);
		flp=hapTemplateService.findByPage(flp);
	    return flp;
	}
	
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request,long id){
		  ModelAndView mv=new ModelAndView();
		  HapTemplate template=hapTemplateService.findById(id);
		  request.setAttribute("template", template);
		  return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(HapTemplate template){
		  ModelAndView mv=new ModelAndView();
		  hapTemplateService.update(template);
		  mv.setViewName("templates/upsert");
		  return mv;
	}
	
	@RequestMapping("/delete")
	public ReturnStatus delete(long  id){
		  ReturnStatus rs=hapTemplateService.deleteById(id);
		  return rs;
	}

}
