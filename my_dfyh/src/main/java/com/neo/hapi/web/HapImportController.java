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
@RequestMapping("/import")
public class HapImportController {
   
	@Autowired
	private HapAccountService hapAccountService;
	
	@RequestMapping("/toQuery")
	public ModelAndView toQuery(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("import_outport/welcome");
	    return mv;
	}
	
	
	
	
	
}
