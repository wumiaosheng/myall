package com.neo.framework.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.neo.hapi.AppContext;
import com.neo.hapi.HapiConstants;

public class RequestInterceptor extends HandlerInterceptorAdapter {

	public static Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	private static final String JSON_PARAMS_KEY = "_json_params";
	

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		AppContext.initSystemEnvironmentContext(request, response);
		String jsonStr = (String) request.getAttribute(JSON_PARAMS_KEY);
		if (jsonStr == null)
			jsonStr = request.getParameter(JSON_PARAMS_KEY);
		if (jsonStr != null) {
			AppContext.putThreadContext(HapiConstants.USER_CONTEXT_JSONOBJ_KEY,
					jsonStr);
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Map fillMap = new HashMap();
		Enumeration attEnu = request.getAttributeNames();
		while (attEnu.hasMoreElements()) {
			String attName = (String) attEnu.nextElement();
			Object fillForm = request.getAttribute(attName);
			if (attName.startsWith("ff")) { // 表单自动回填
				attName = attName.substring(2);
				fillMap.put(attName, fillForm);
			}
		}
		if (fillMap.size() > 0) {
			String fillMapStr = mapper.writeValueAsString(fillMap);
			request.setAttribute("_FILL_MAP",fillMapStr.replace("</script>", "<//script>"));
		}
		request.setAttribute("UserContext", AppContext.currentUser());
		request.setAttribute("ctxPath",request.getServletContext().getContextPath());
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
		try {
			if (request.getRequestURI().indexOf("toLogin") != -1) {
				if (ex == null) {

				}
			}
		} catch (Exception e) {
			// ignore
		}
	}
}
