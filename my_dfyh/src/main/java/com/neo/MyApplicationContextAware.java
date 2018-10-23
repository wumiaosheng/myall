package com.neo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class MyApplicationContextAware implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext wac)
			throws BeansException {
		WebApplicationContext wa = (WebApplicationContext)wac;
		//SystemLoader.initBeforeSpring(wa.getServletContext());
		//SystemLoader.initAfterSpring(wa.getServletContext(), wac);
	}

}
