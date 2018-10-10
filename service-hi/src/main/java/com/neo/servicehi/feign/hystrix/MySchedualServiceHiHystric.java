package com.neo.servicehi.feign.hystrix;

import org.springframework.stereotype.Component;

import com.neo.servicehi.feign.MySchedualServiceHi;

@Component
public class MySchedualServiceHiHystric implements MySchedualServiceHi {
   
	@Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}