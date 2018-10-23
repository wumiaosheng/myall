package com.neo.hapi.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.entity.ReturnStatus;
import com.neo.common.util.RedisUtil;
import com.neo.framework.util.StringUtils;
import com.neo.hapi.AppContext;
import com.neo.hapi.Constants;
import com.neo.hapi.UserContext;
import com.neo.hapi.dao.HapRoleDao;
import com.neo.hapi.entity.HapUserAccount;
import com.neo.hapi.feign.SchedualServiceHi;
import com.neo.hapi.service.LoginService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

@RestController
public class HomeController {

	@Autowired
	private HapRoleDao hapRoleDao;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private SchedualServiceHi schedualServiceHi;
    
	@Autowired
	private RedisUtil redisUtil;
	
	@RequestMapping("/login")
    public ModelAndView toLogin() {
		
		redisUtil.set("test", "1234567890");
		ModelAndView mv =new ModelAndView();
		mv.setViewName("login");
		return mv;
     }
	
	@RequestMapping("/submitLogin")
    public ModelAndView submitLogin(HttpServletRequest request,HapUserAccount hua) {
		ModelAndView mv=new ModelAndView();
		ReturnStatus rs=new ReturnStatus(false);
		///用户名不能为空////密码不能为空
		if(StringUtils.isEmpty(hua.getAccount())
				||StringUtils.isEmpty(hua.getPassword())){
			rs.setMessage("用户名或密码不能为空");
			//rs.setCode(MErrorCode.e9000);
			mv.setViewName("login");
			return mv;
		}
		////验证码错误
		UserContext uc = loginService.transLogin(hua);
		if(uc!=null){
			AppContext.putSessionContext(Constants.USERCONTEXT, uc);
			request.setAttribute("menus", uc.getMenu().getChildMenu().get(0).getChildMenu());
		}else{
			mv.setViewName("login");
			return mv;	
		}
		mv.setViewName("index");
		return mv;
    }
	
	@RequestMapping("/logout")
    public ModelAndView logout() {
		ModelAndView mv =new ModelAndView();
		mv.setViewName("login");
		return mv;
    }
	
	 @RequestMapping("/")
     public ModelAndView toIndex() {
		ModelAndView mv =new ModelAndView();
		mv.setViewName("login");
		return mv;
     }
	 
	 @RequestMapping("/welcome")
     public ModelAndView welcome(HttpServletRequest request){
		String name=schedualServiceHi.sayHiFromClientOne("吴妙生");
		
		request.setAttribute("myname", name);
		ModelAndView mv =new ModelAndView();
		
		mv.setViewName("welcome");
		return mv;
     }
	 
	    @GetMapping("/forMy")
	    public String forMy(String name) {
	        return "123456 你好:"+name;
	    }
	 
		/* 其中 /unsafe 接口直接请求 schedualServiceHi 
		 而 /safe 通过 Hystrix 包装后请求 schedualServiceHi*/
	    @GetMapping("/safe")
	    public String safe() {
	        return new com.netflix.hystrix.HystrixCommand<String>(setter()) {
	            @Override
	            protected String run() throws Exception {
	            	schedualServiceHi.sayHiFromClientOne("吴妙生");
	                return "OK";
	            }
	        }.execute();
	    }

	    @GetMapping("/unsafe")
	    public String unsafe() {
	        return schedualServiceHi.sayHiFromClientOne("吴妙生");
	    }
	    
	    private com.netflix.hystrix.HystrixCommand.Setter setter() {
	        return HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("External"))
	            .andCommandKey(HystrixCommandKey.Factory.asKey("/safe"));
	    }

}
