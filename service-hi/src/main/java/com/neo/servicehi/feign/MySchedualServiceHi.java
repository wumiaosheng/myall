package com.neo.servicehi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neo.servicehi.feign.hystrix.MySchedualServiceHiHystric;

@FeignClient(value = "bjdfyh" ,fallback = MySchedualServiceHiHystric.class)
public interface MySchedualServiceHi {
	
    @RequestMapping(value = "/forMy")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

}

