package com.neo.servicehi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neo.servicehi.feign.MySchedualServiceHi;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class HomeController {
 
	
	    @Autowired
	    private MySchedualServiceHi mySchedualServiceHi;
	    

	    @RequestMapping("/hi")
	    @HystrixCommand(fallbackMethod = "hiError")
	    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
	        return "hi " + name;
	    }
	    
	    
	    @RequestMapping("/forMy")
	    public String forMy(){
	    	return mySchedualServiceHi.sayHiFromClientOne("吴妙生");
	    }
	    
	    ///断路器
	    public String hiError(String name) {
	        return "hi,"+name+",sorry,error!";
	    }
}
