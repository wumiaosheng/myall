package com.neo.hapi;

public class HapiConstants {

	public static final String USERID = "{USERID}";

	public static final String ORGID = "{ORGID}";

	public static final String ORGCODE = "{ORGCODE}";

	public static final String USERNAME = "{USERNAME}";

	public static final String ORGNAME = "{ORGNAME}";

	public static final String MEMO = "{MEMO}";

	public static final String REGIONCODE = "{REGIONCODE}";

	public static final String SCENARIOID = "SCENARIOID";

	public static final String FUNC_URLDEFINE_PREFIX = "url:";
	public static final String FUNC_UIDEFINE_PREFIX = "ui:";
	public static final String FUNC_DATADEFINE_PREFIX = "data:";
	public static final String FUNC_AJAXDEFINE_PREFIX = "ajax:";
	public static final String FUNC_PORTLETDEFINE_PREFIX = "portlet:";
	public static final String PORTAL_DIRECTORY = "/templates";

	public static final String SESSION_CONTEXT_USERINFO_KEY = "SESSION_CONTEXT_USERINFO_KEY";
	public static final String SESSION_CURRENT_USER = "SESSION_CURRENT_USER";

	/**
	 * 表单自动回填JSON字符串变量的Request Attribute存储KEY
	 */
	public static final String USER_CONTEXT_JSONOBJ_KEY = "USER_CONTEXT_JSONOBJ_KEY";

	/**
	 * 每次请求类型存储KEY，包括CS和BS两种请求类型
	 */
	public static final String USER_CONTEXT_REQTYPE_KEY = "USER_CONTEXT_REQTYPE_KEY";

	/**
	 * 请求Session级存储的用户访问的资源入口UUID
	 */
	public static final String SESSION_CONTEXT_RESUUID_KEY = "SESSION_CONTEXT_RESUUID_KEY";

	/**
	 * 请求Session级存储的用户上一次访问JSON参数
	 */
	public static final String SESSION_CONTEXT_LASTJSON_KEY = "SESSION_CONTEXT_LASTJSON_KEY";

	/**
	 * 请求Session级存储的用户上上一次访问路径
	 */
	public static final String SESSION_CONTEXT_LLASTURI_KEY = "SESSION_CONTEXT_LLASTURI_KEY";

	/**
	 * 请求Session级存储的用户上上一次访问参数
	 */
	public static final String SESSION_CONTEXT_LLASTPARAS_KEY = "SESSION_CONTEXT_LLASTPARAS_KEY";

	/**
	 * 请求Session级存储的用户上上一次访问JSON参数
	 */
	public static final String SESSION_CONTEXT_LLASTJSON_KEY = "SESSION_CONTEXT_LLASTJSON_KEY";

}
