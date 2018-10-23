/**
 * $Author: wuym $
 * $Rev: 155 $
 * $Date:: 2012-07-09 15:08:37#$:
 *
 * Copyright (C) 2012 Seeyon, Inc. All rights reserved.
 *
 * This software is the proprietary information of Seeyon, Inc.
 * Use is subject to license terms.
 */
package com.neo.framework.entity;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * <p>
 * Title: T1开发框架
 * </p>
 * <p>
 * Description: 业务异常类，支持国际化消息转换，同时当引发的根源异常为非BusinessException类型时框架
 * 将作为异常处理，反之则作为用户提示消息处理
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: seeyon.com
 * </p>
 */
public class AppException extends Exception {

	private static final long serialVersionUID = -9190857360219190477L;

	// 异常或提示消息代码
	private String code;
	// 异常提示消息是否全页面显示（有全页面的展现样式），默认嵌入局部页面模式（比如message弹出消息框中）
	private boolean fullPage = false;

	/**
	 * 默认构造方法
	 */
	public AppException() {
		super();
	}

	/**
	 * 根据消息构造异常，首先会从国际化资源中查找，如果未找到则使用message作为异常消息
	 * 
	 * @param message
	 *            消息
	 */
	public AppException(String message) {
		super(message);
	}

	/**
	 * 根据引发异常构造异常
	 * 
	 * @param cause
	 *            引发异常
	 */
	public AppException(Throwable cause) {
		super(cause);
	}

	/**
	 * 根据消息和引发异常构造异常
	 * 
	 * @param message
	 *            消息
	 * @param cause
	 *            引发异常
	 */
	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 异常输出，找到非BusinessException类型的引发异常进行打印，否则视为提示信息处理
	 */
	public void printStackTrace() {
		Throwable t = getRawCause();
		if (!(t instanceof AppException) && t != null) {
			t.printStackTrace();
		} else {
			// 提示型异常，忽略stack trace
		}
	}

	/**
	 * 异常输出到指定输出流，找到非BusinessException类型的引发异常进行打印，否则视为提示信息处理
	 * 
	 * @param pw
	 *            打印输出流
	 */
	public void printStackTrace(PrintWriter pw) {
		Throwable t = getRawCause();
		if (!(t instanceof AppException) && t != null) {
			t.printStackTrace(pw);
		} else {
			// 提示型异常，忽略stack trace
		}
	}

	/**
	 * 异常输出到指定输出流，找到非BusinessException类型的引发异常进行打印，否则视为提示信息处理
	 * 
	 * @param s
	 *            打印输出流
	 */
	public void printStackTrace(PrintStream s) {
		Throwable t = getRawCause();
		if (!(t instanceof AppException) && t != null) {
			t.printStackTrace(s);
		} else {
			// 提示型异常，忽略stack trace
		}
	}

	/**
	 * 获得非BusinessException类型的根源引发异常
	 * 
	 * @return 非BusinessException类型的根源引发异常
	 */
	public Throwable getRawCause() {
		Throwable ct = super.getCause();
		if (ct instanceof AppException)
			return ((AppException) ct).getRawCause();
		else {
			return ct;
		}
	}

	/**
	 * BusinessException异常嵌套是获得根源BusinessException异常
	 * 
	 * @return 根源BusinessException异常
	 */
	public AppException getRawBusinessException() {
		Throwable t = getCause();
		if (t instanceof AppException)
			return ((AppException) t).getRawBusinessException();
		else
			return this;
	}

	/**
	 * 获取异常/提示消息代码
	 * 
	 * @return 异常/提示消息代码
	 */
	public String getCode() {
		if (code == null) {
			String curm = String.valueOf(System.currentTimeMillis());
			setCode(curm.substring(curm.length() - 10));
		}
		return code;
	}

	/**
	 * 设置异常/提示消息代码
	 * 
	 * @param code
	 *            异常/提示消息代码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 异常提示消息是否全页面显示（有全页面的展现样式），默认嵌入局部页面模式（比如message弹出消息框中）
	 * 
	 * @return true 表示异常信息全页面展现，否则不带任何样式嵌入比如message弹出消息框中
	 */
	public boolean isFullPage() {
		return fullPage;
	}

	/**
	 * 设置异常提示消息是否全页面显示（有全页面的展现样式），默认嵌入局部页面模式（比如message弹出消息框中）
	 * 
	 * @param fullPage
	 *            异常提示消息是否全页面显示（有全页面的展现样式），默认嵌入局部页面模式（比如message弹出消息框中）
	 */
	public void setFullPage(boolean fullPage) {
		this.fullPage = fullPage;
	}

	public AppException getRawAppException() {
		Throwable t = getCause();
		if (t instanceof AppException)
			return ((AppException) t).getRawAppException();
		else
			return this;
	}

}
