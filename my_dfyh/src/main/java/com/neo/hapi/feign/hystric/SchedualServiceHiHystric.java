package com.neo.hapi.feign.hystric;

import org.springframework.stereotype.Component;

import com.neo.hapi.feign.SchedualServiceHi;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
   
	@Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}

