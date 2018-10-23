/**
 * $Author: wuym $
 * $Rev: 8260 $
 * $Date:: 2013-07-03 16:23:47#$:
 *
 * Copyright (C) 2012 Seeyon, Inc. All rights reserved.
 *
 * This software is the proprietary information of Seeyon, Inc.
 * Use is subject to license terms.
 */
package com.neo.hapi;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.neo.hapi.entity.HapUserAccount;

/**
 * <p>
 * Title: T1开发框架
 * </p>
 * <p>
 * Description: 框架上下文相关操作工具类，包括获取当前登录用户相关信息、线程变量和Session操作方法、 配置文件夹操作等
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: seeyon.com
 * </p>
 * 
 * @since CTP2.0
 */
public final class AppContext {

	private static final Logger LOGGER = Logger.getLogger(AppContext.class);

	private static ApplicationContext ac = null;

	private static File CFG_HOME = null;

	@SuppressWarnings("rawtypes")
	private static final ThreadLocal userCtx = new ThreadLocal();

	// 框架级缓存Map
	private static final Map<String, Object> cacheMap = new HashMap<String, Object>();

	// spring bean缓存
	private static final Map<String, Object> beanCacheMap = new HashMap<String, Object>();

	// spring beanOfType缓存
	private static final Map<Class<?>, Map<String, ?>> beanOfTypeCacheMap = new HashMap<Class<?>, Map<String, ?>>();

	/**
	 * 初始化Spring应用上下文和系统配置目录
	 * 
	 * @param wac
	 *            Spring应用上下文
	 * @param cfgHome
	 *            系统配置目录
	 */
	public static void init(ApplicationContext wac, File cfgHome) {
		ac = wac;
		CFG_HOME = cfgHome;
	}

	/** ************** 当前用户上下文开始 ************************** */

	public static UserContext currentUser() {
		if (getThreadContext("_TESTING") != null) { // 支持单元测试
			UserContext uc = new UserContext();
			HapUserAccount ua = new HapUserAccount();
			uc.setUserAccount(ua);
			return uc;
		} else
			return (UserContext) getSessionContext(Constants.USERCONTEXT);
	}

	public static long currentUserId() {
		UserContext uc = AppContext.currentUser();
		if (uc != null)
			return uc.getUserAccount().getId();
		else
			return 0;
	}

	/** ************** 当前用户上下文结束 ************************** */

	/**
	 * 根据Spring管理的Bean名称获取Bean实例（带bean缓存以优化性能），非singleton的bean不适用
	 * 
	 * @param beanName
	 *            Spring管理的Bean名称
	 * @return Bean实例
	 */
	public static Object getBean(String beanName) {
		Object bean = beanCacheMap.get(beanName);
		if (bean == null) {
			bean = ac.getBean(beanName);
			beanCacheMap.put(beanName, bean);
		}
		return bean;
	}

	/**
	 * 根据Spring管理的Bean名称获取Bean实例，不走性能优化缓存，适用于非singleton的bean获取
	 * 
	 * @param beanName
	 *            Spring管理的Bean名称
	 * @return Bean实例
	 */
	public static Object getBeanWithoutCache(String beanName) {
		return ac.getBean(beanName);
	}

	/**
	 * 根据Spring管理的Bean类型获取Bean实例Map，key为bean id或name，value为Bean实例
	 * 
	 * @param type
	 *            要获取Spring管理的Bean的类型，比如接口、抽象类
	 * @return Spring容器中指定类型的Bean实例Map，key为bean id或name，value为Bean实例
	 */
	public static <T> Map<String, T> getBeansOfType(Class<T> type) {
		Map<String, T> result = (Map<String, T>) beanOfTypeCacheMap.get(type);
		if (result == null) {
			result = ac.getBeansOfType(type);
			beanOfTypeCacheMap.put(type, result);
		}
		return result;
	}

	/**
	 * 获取配置文件存放根目录
	 * 
	 * @return 配置文件存放根目录
	 */
	public static File getCfgHome() {
		if (CFG_HOME == null || !CFG_HOME.exists() || !CFG_HOME.isDirectory()) {
			throw new InfrastructureException();
		}
		return CFG_HOME;
	}

	/**
	 * 设置线程上下文参数
	 * 
	 * @param ctxKey
	 *            上下文键
	 * @param ctxValue
	 *            上下文值
	 */
	public static void putThreadContext(String ctxKey, Object ctxValue) {
		Map ctxMap = (Map) userCtx.get();
		if (ctxMap == null) {
			ctxMap = new HashMap();
			userCtx.set(ctxMap);
		}
		ctxMap.put(ctxKey, ctxValue);
	}

	/**
	 * 获取线程上下文参数
	 * 
	 * @param ctxKey
	 *            上下文键
	 * @return 上下文值
	 */
	public static Object getThreadContext(String ctxKey) {
		Map ctxMap = (Map) userCtx.get();
		if (ctxMap == null)
			return null;
		else
			return ctxMap.get(ctxKey);
	}

	/**
	 * 删除线程上下文参数
	 * 
	 * @param ctxKey
	 *            上下文键
	 */
	public static void removeThreadContext(String ctxKey) {
		Map ctxMap = (Map) userCtx.get();
		if (ctxMap != null)
			ctxMap.remove(ctxKey);
	}

	/**
	 * 清除所有线程变量
	 */
	public static void clearThreadContext() {
		userCtx.set(null);
	}

	public static void putCache(String key, Object value) {
		cacheMap.put(key, value);
	}

	public static Object getCache(String key) {
		return cacheMap.get(key);
	}

	/**
	 * 设置session级上下文参数
	 * 
	 * @param ctxKey
	 *            上下文键
	 * @param ctxValue
	 *            上下文值
	 */
	public static void putSessionContext(String ctxKey, Object ctxValue) {
		HttpSession session = (HttpSession) getThreadContext(Constants.THREAD_CONTEXT_SESSION_KEY);
		if (session != null)
			session.setAttribute(ctxKey, ctxValue);
		else
			throw new InfrastructureException("Session isnot in current thread context.");
	}

	/**
	 * 获取session级上下文参数
	 * 
	 * @param ctxKey
	 *            上下文键
	 * @return 上下文值
	 */
	public static Object getSessionContext(String ctxKey) {
		HttpSession session = (HttpSession) getThreadContext(Constants.THREAD_CONTEXT_SESSION_KEY);
		if (session != null){
			try{
				return session.getAttribute(ctxKey);
			}catch(Exception e){
				LOGGER.error("THREAD_CONTEXT_SESSION_KEY----session失效");
				//如果当前线程绑定的session失效，则将失效session移除
				removeThreadContext(Constants.THREAD_CONTEXT_SESSION_KEY);
			}
			return null;
		}else{
			return null;
		}
		/*
		 * throw new InfrastructureException(
		 * "Session isnot in current thread context.");
		 */
	}

	/**
	 * 获取原始HttpSession对象，不建议应用直接使用
	 * 
	 * @return HttpSession实例
	 */
	public static HttpSession getRawSession() {
		return (HttpSession) getThreadContext(Constants.THREAD_CONTEXT_SESSION_KEY);
	}

	/**
	 * 设置request级上下文参数
	 * 
	 * @param ctxKey
	 *            上下文键
	 * @param ctxValue
	 *            上下文值
	 */
	public static void putRequestContext(String ctxKey, Object ctxValue) {
		HttpServletRequest request = (HttpServletRequest) getThreadContext(Constants.THREAD_CONTEXT_REQUEST_KEY);
		if (request != null)
			request.setAttribute(ctxKey, ctxValue);
		else
			throw new InfrastructureException(
					"Request isnot in current thread context.");
	}

	/**
	 * 获取request级上下文参数
	 * 
	 * @param ctxKey
	 *            上下文键
	 * @return 上下文值
	 */
	public static Object getRequestContext(String ctxKey) {
		HttpServletRequest request = (HttpServletRequest) getThreadContext(Constants.THREAD_CONTEXT_REQUEST_KEY);
		if (request != null)
			return request.getAttribute(ctxKey);
		else
			throw new InfrastructureException(
					"Request isnot in current thread context.");
	}

	/**
	 * 获取原始HttpServletRequest对象，不建议应用直接使用
	 * 
	 * @return HttpServletRequest实例
	 */
	public static HttpServletRequest getRawRequest() {
		return (HttpServletRequest) getThreadContext(Constants.THREAD_CONTEXT_REQUEST_KEY);
	}

	/**
	 * 获取原始HttpServletResponse对象，不建议应用直接使用
	 * 
	 * @return HttpServletResponse实例
	 */
	public static HttpServletResponse getRawResponse() {
		return (HttpServletResponse) getThreadContext(Constants.THREAD_CONTEXT_RESPONSE_KEY);
	}

	/**
	 * 判断当前登陆用户是否具备指定资源Code的权限
	 * 
	 * @param resourceCode
	 *            资源Code
	 * @return 如果当前登陆用户具有指定资源Code权限则返回true，否则返回false
	 */
	public static boolean hasResourceCode(String resourceCode) {
		// return getCurrentUser().hasResourceCode(resourceCode);
		return true;
	}

	/**
	 * 初始化系统环境上下文，在请求入口调用，比如Ajax、controller、servlet等，否则会引起应用 功能异常
	 * 
	 * @param request
	 *            Servlet请求对象
	 * @param response
	 *            Servlet应答对象
	 */
	public static void initSystemEnvironmentContext(HttpServletRequest request,
			HttpServletResponse response) {
		initSystemEnvironmentContext(request, response, true);
	}

	/**
	 * 初始化系统环境上下文，在请求入口调用，比如Ajax、controller、servlet等，否则会引起应用 功能异常
	 * 
	 * @param request
	 *            Servlet请求对象
	 * @param response
	 *            Servlet应答对象
	 * @param session
	 *            是否session不存在则创建,request.getSession(true or false)
	 */
	public static void initSystemEnvironmentContext(HttpServletRequest request,
			HttpServletResponse response, boolean session) {
		HttpSession httpSession = request.getSession(session);
		AppContext.putThreadContext(Constants.THREAD_CONTEXT_SESSION_KEY,httpSession);
		if (httpSession != null)
		AppContext.putThreadContext(HapiConstants.SESSION_CONTEXT_USERINFO_KEY,AppContext.getSessionContext(HapiConstants.SESSION_CURRENT_USER));
		AppContext.putThreadContext(Constants.THREAD_CONTEXT_REQUEST_KEY,request);
		AppContext.putThreadContext(Constants.THREAD_CONTEXT_RESPONSE_KEY,response);
	}

}
