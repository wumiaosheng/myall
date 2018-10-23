package com.neo.framework.interceptor.mybatis;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neo.common.entity.FlipInfo;
import com.neo.framework.util.DateUtil;
import com.neo.framework.util.StringUtils;



@Intercepts({
		@Signature(method = "prepare", type = StatementHandler.class, args = {
				Connection.class, Integer.class }),
		@Signature(method = "query", type = Executor.class, args = {
				MappedStatement.class, Object.class, RowBounds.class,
				ResultHandler.class }) })
public class PageInterceptor implements Interceptor {

	private static final Logger log = LoggerFactory
			.getLogger(PageInterceptor.class);

	public static final String MYSQL = "mysql";
	public static final String ORACLE = "oracle";

	protected String databaseType;// 数据库类型，不同的数据库有不同的分页方法

	protected ThreadLocal<FlipInfo> pagingThreadLocal = new ThreadLocal<FlipInfo>();

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		if (!databaseType.equalsIgnoreCase(MYSQL)
				&& !databaseType.equalsIgnoreCase(ORACLE)) {
			throw new FlipInfoNotSupportException(
					"FlipInfo not support for the type of database, database type ["
							+ databaseType + "]");
		}
		this.databaseType = databaseType;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		String databaseType = properties.getProperty("databaseType");
		if (databaseType != null) {
			setDatabaseType(databaseType);
		}
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object intercept(Invocation invocation) throws Throwable {
		if (invocation.getTarget() instanceof StatementHandler) {// 控制SQL和查询总数的地方
			FlipInfo fpi = pagingThreadLocal.get();
			if (fpi == null || fpi.isAll()) { // 不是分页查询
				return invocation.proceed();
			}

			RoutingStatementHandler handler = (RoutingStatementHandler) invocation
					.getTarget();
			StatementHandler delegate = (StatementHandler) ReflectUtil
					.getFieldValue(handler, "delegate");
			BoundSql boundSql = delegate.getBoundSql();
			Connection connection = (Connection) invocation.getArgs()[0];
			prepareAndCheckDatabaseType(connection); // 准备数据库类型

			if (fpi.isNeedTotal()) {
				Object parameterObj = boundSql.getParameterObject();
				MappedStatement mappedStatement = (MappedStatement) ReflectUtil
						.getFieldValue(delegate, "mappedStatement");
				queryTotalRecord(fpi, parameterObj, mappedStatement, connection);
				//已查询过，本次不再需要分页
				fpi.setNeedTotal(false);
				fpi.setAll(true);
			}

			String sql = boundSql.getSql();
			String pagingSql = buildPagingSql(fpi, sql);
			if (log.isDebugEnabled()) {
				log.debug("分页时, 生成分页PagingSql: " + pagingSql);
			}
			ReflectUtil.setFieldValue(boundSql, "sql", pagingSql);

			return invocation.proceed();
		} else { // 查询结果的地方
			// 获取是否有分页FlipInfo对象
			FlipInfo<?> fpi = findFlipInfoObject(invocation.getArgs()[1]);
			if (fpi == null) {
				if (log.isTraceEnabled()) {
					log.trace("没有FlipInfo对象作为参数, 不是分页查询.");
				}
				return invocation.proceed();
			} else {
				if (log.isTraceEnabled()) {
					log.trace("检测到分页FlipInfo对象, 使用分页查询.");
				}
			}
			// 设置真正的parameterObj
			invocation.getArgs()[1] = extractRealParameterObject(invocation
					.getArgs()[1]);

			pagingThreadLocal.set(fpi);
			try {
				Object resultObj = invocation.proceed(); // Executor.query(..)
				if (resultObj instanceof List) {
					/* @SuppressWarnings({ "unchecked", "rawtypes" }) */
					fpi.setData((List) resultObj);
				}
				return resultObj;
			} finally {
				pagingThreadLocal.remove();
			}
		}
	}

	protected FlipInfo<?> findFlipInfoObject(Object parameterObj) {
		if (parameterObj instanceof FlipInfo<?>) {
			return (FlipInfo<?>) parameterObj;
		} else if (parameterObj instanceof Map) {
			for (Object val : ((Map<?, ?>) parameterObj).values()) {
				if (val instanceof FlipInfo<?>) {
					return (FlipInfo<?>) val;
				} else if (val instanceof Map) {
					for (Object v : ((Map<?, ?>) parameterObj).values()) {
						if (v instanceof FlipInfo<?>) {
							return (FlipInfo<?>) v;
						}
					}
				}
			}
		}
		return null;
	}

	protected Object extractRealParameterObject(Object parameterObj) {
		Map<?, ?> returnMap;
		if (parameterObj instanceof Map<?, ?>) {
			Map<?, ?> parameterMap = (Map<?, ?>) parameterObj;
			Map resultMap = new HashMap();
			boolean hasFlipInfo = false;
			for (Object key : parameterMap.keySet()) {
				Object value = parameterMap.get(key);
				if (value instanceof FlipInfo) {
					hasFlipInfo = true;
					FlipInfo fpi = (FlipInfo) value;
					Map params = fpi.getParams();
					putFlipInfoOrderBy(params, fpi);
					resultMap.putAll(params);
				} else {
					resultMap.put(key, value);
				}
			}
			if (hasFlipInfo) // 翻页查询时才需要做参数处理
				returnMap = resultMap;
			else
				returnMap = parameterMap;
		} else if (parameterObj instanceof FlipInfo) {
			FlipInfo fpi = (FlipInfo) parameterObj;
			Map params = fpi.getParams();
			putFlipInfoOrderBy(params, fpi);
			returnMap = params;
		} else
			return parameterObj;

		Map m = new HashMap();
		for (Object key : returnMap.keySet()) {
			Object o = returnMap.get(key);
			if (o != null
					&& (!(o instanceof String) || !StringUtils
							.isEmpty((String) o))) {
				if (key instanceof String) {
					String s = (String) key;
					String condition = "is";
					String k = s;
					String type = null;
					String[] tokens = k.split("\\:");
					String connector = null;// 连接符
					if (tokens.length == 2) {
						condition = tokens[0];
						k = tokens[1];
					}
					tokens = k.split("\\|");
					if (tokens.length == 2) {
						k = tokens[0];
						type = tokens[1];
						tokens = type.split("#");
						if (tokens.length == 2) {
							type = tokens[0];
							connector = tokens[1];
						}
					}

					if (o instanceof String) {
						o = extractValue(type, (String) o);
					}

					if (condition.equalsIgnoreCase("ignore")) {
						continue;
					} else if (condition.equalsIgnoreCase("regex")) {
						m.put(k, "%" + o + "%");
					} else
						m.put(k, o);
				} else
					m.put(key, o);
			}
		}

		return m;
	}

	private Object extractValue(String type, String value) {
		Object result = value;
		if ("boolean".equalsIgnoreCase(type)) {
			result = Boolean.parseBoolean(value);
		} else if ("integer".equalsIgnoreCase(type)) {
			result = Integer.parseInt(value);
		} else if ("long".equalsIgnoreCase(type)) {
			result = Long.parseLong(value);
		} else if ("double".equalsIgnoreCase(type)) {
			result = Double.parseDouble(value);
		} else if ("float".equalsIgnoreCase(type)) {
			result = Float.parseFloat(value);
		} else if ("date".equalsIgnoreCase(type)) {
			try {
				result = DateUtil.parse(value, DateUtil.YEAR_MONTH_DAY_PATTERN);
			} catch (ParseException e) {
				throw new RuntimeException("参数日期格式错误，应为：yyyy-MM-dd", e);
			}
		} else if ("date+1".equalsIgnoreCase(type)) {
			try {
				result = DateUtil.addDate(
						DateUtil.parse(value, DateUtil.YEAR_MONTH_DAY_PATTERN),
						1);
			} catch (ParseException e) {
				throw new RuntimeException("参数日期格式错误，应为：yyyy-MM-dd", e);
			}
		} else if ("datetime".equalsIgnoreCase(type)) {
			try {
				result = DateUtil.parse(value, DateUtil.YMDHMS_PATTERN);
			} catch (ParseException e) {
				throw new RuntimeException("参数日期格式错误，应为：yyyy-MM-dd HH:mm:ss", e);
			}
		} else if ("array-integer".equalsIgnoreCase(type)) {
			List<String> vs = Arrays.asList(value.split(","));
			result = new ArrayList<Integer>();
			for (String v : vs) {
				((List) result).add(new Integer(v));
			}
		} else if ("array".equalsIgnoreCase(type)) {
			result = Arrays.asList(value.split(","));
		} else if ("null".equalsIgnoreCase(type)) {
			result = null;
		} else if ("empty".equalsIgnoreCase(type)) {
			result = "";
		}

		return result;
	}

	private void putFlipInfoOrderBy(Map params, FlipInfo fpi) {
		String sortField = (String) params.get("sidx");
		String sortOrder = (String) params.get("sord");
		String sf = null, so = null;
		if (!StringUtils.isEmpty(sortField)) {
			sf = sortField;
			so = sortOrder;
		} else if (!StringUtils.isEmpty(fpi.getSortField())) {
			sf = fpi.getSortField();
			so = fpi.getSortOrder();
		}
		if (sf != null && !"".equals(sf)) {
			// 根据java字段和数据库字段命名规则转换排序字段名称，如:myField -> my_field
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < sf.length(); i++) {
				char c = sf.charAt(i);
				if (Character.isUpperCase(c)) {
					buf.append('_').append(Character.toLowerCase(c));
				} else {
					buf.append(c);
				}
			}
			sf = buf.toString();
			// 解决非唯一字段排序时，部分重复区间数据相同的问题
			if (!"id".equals(sf))
				so += ",id";
			params.put("orderBy", sf + " " + so);
		}
	}

	protected void prepareAndCheckDatabaseType(Connection connection)
			throws SQLException {
		if (databaseType == null) {
			String productName = connection.getMetaData()
					.getDatabaseProductName();
			if (log.isTraceEnabled()) {
				log.trace("Database productName: " + productName);
			}
			productName = productName.toLowerCase();
			if (productName.indexOf(MYSQL) != -1) {
				databaseType = MYSQL;
			} else if (productName.indexOf(ORACLE) != -1) {
				databaseType = ORACLE;
			} else {
				throw new FlipInfoNotSupportException(
						"FlipInfo not support for the type of database, database product name ["
								+ productName + "]");
			}
			if (log.isInfoEnabled()) {
				log.info("自动检测到的数据库类型为: " + databaseType);
			}
		}
	}

	/**
	 * <pre>
	 * 生成分页SQL
	 * </pre>
	 * 
	 * @author jundong.xu_C
	 * @param fpi
	 * @param sql
	 * @return
	 */
	protected String buildPagingSql(FlipInfo<?> fpi, String sql) {
		sql = sql.replace('^', ' ');
		if (MYSQL.equalsIgnoreCase(databaseType)) {
			return buildMysqlFlipInfoSql(fpi, sql);
		} else if (ORACLE.equalsIgnoreCase(databaseType)) {
			return buildOracleFlipInfoSql(fpi, sql);
		}
		return sql;
	}

	/**
	 * <pre>
	 * 生成Mysql分页查询SQL
	 * </pre>
	 * 
	 * @author jundong.xu_C
	 * @param fpi
	 * @param sql
	 * @return
	 */
	protected String buildMysqlFlipInfoSql(FlipInfo<?> fpi, String sql) {
		// 计算第一条记录的位置，Mysql中记录的位置是从0开始的。
		int offset = (fpi.getPage() - 1) * fpi.getSize();
		return new StringBuilder(sql).append(" limit ").append(offset)
				.append(",").append(fpi.getSize()).toString();
	}

	/**
	 * <pre>
	 * 生成Oracle分页查询SQL
	 * </pre>
	 * 
	 * @author jundong.xu_C
	 * @param fpi
	 * @param sql
	 * @return
	 */
	protected String buildOracleFlipInfoSql(FlipInfo<?> fpi, String sql) {
		// 计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的
		int offset = (fpi.getPage() - 1) * fpi.getSize() + 1;
		StringBuilder sb = new StringBuilder(sql);
		sb.insert(0, "select u.*, rownum r from (")
				.append(") u where rownum < ").append(offset + fpi.getSize());
		sb.insert(0, "select * from (").append(") where r >= ").append(offset);
		return sb.toString();
	}

	/**
	 * <pre>
	 * 查询总数
	 * </pre>
	 * 
	 * @author jundong.xu_C
	 * @param fpi
	 * @param parameterObject
	 * @param mappedStatement
	 * @param connection
	 * @throws SQLException
	 */
	protected void queryTotalRecord(FlipInfo<?> fpi, Object parameterObject,
			MappedStatement mappedStatement, Connection connection)
			throws SQLException {
		BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
		String sql = boundSql.getSql();
		String countSql = this.buildCountSql(sql);
		if (log.isDebugEnabled()) {
			log.debug("分页时, 生成countSql: " + countSql);
		}

		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		BoundSql countBoundSql = new BoundSql(
				mappedStatement.getConfiguration(), countSql,
				parameterMappings, parameterObject);
//		copyAdditionalParameters(boundSql, countBoundSql);
//		copyMetaParameters(boundSql, countBoundSql);
        Map<String, Object> map = (Map<String, Object>) ReflectUtil.getFieldValue(boundSql, "additionalParameters");
        ReflectUtil.setFieldValue(countBoundSql, "additionalParameters", map);
        MetaObject mo = (MetaObject) ReflectUtil.getFieldValue(boundSql, "metaParameters");
        ReflectUtil.setFieldValue(countBoundSql, "metaParameters", mo);
//        DynamicContext context = new DynamicContext(mappedStatement.getConfiguration(), parameterObject);
//        for (Map.Entry<String, Object> entry : context.getBindings().entrySet()) {
//        	countBoundSql.setAdditionalParameter(entry.getKey(), entry.getValue());
//          }
		ParameterHandler parameterHandler = new DefaultParameterHandler(
				mappedStatement, parameterObject, countBoundSql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(countSql);
			parameterHandler.setParameters(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				long totalRecord = rs.getLong(1);
				fpi.setTotal((int) totalRecord);
			}
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
					if (log.isWarnEnabled()) {
						log.warn("关闭ResultSet时异常.", e);
					}
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
					if (log.isWarnEnabled()) {
						log.warn("关闭PreparedStatement时异常.", e);
					}
				}
		}
	}

	/**
	 * 根据原Sql语句获取对应的查询总记录数的Sql语句
	 * 
	 * @param sql
	 * @return
	 */
	protected String buildCountSql(String sql) {
		String rsql = sql, countSql;
		String lowerCase = rsql.toLowerCase();

		int widx = lowerCase.indexOf(" ^ "), idx = lowerCase.indexOf("from"), orderIdx;
		if (idx == -1)
			throw new IllegalAccessError(
					"Illegal sql that not contain from keyword.");
		if (widx != -1)
			idx = widx + 2;
		if (lowerCase.indexOf("group by") != -1
				|| lowerCase.indexOf("distinct") != -1) {
			// 查询记录数时去掉order by以优化性能
			orderIdx = lowerCase.indexOf("order by");
			String tempSql = rsql;
			if (orderIdx != -1) {
				tempSql = tempSql.substring(0, orderIdx);
			}
			countSql = "select count(1) from (" + tempSql + ") a";
			countSql = countSql.replace("^","");
		} else {
			// 查询记录数时去掉order by以优化性能
			orderIdx = lowerCase.indexOf("order by");
			if (orderIdx != -1) {
				countSql = "select count(*) " + rsql.substring(idx, orderIdx);
			} else {
				countSql = "select count(*) " + rsql.substring(idx);
			}
		}
		return countSql;
	}

	/**
	 * 利用反射进行操作的一个工具类
	 * 
	 */
	private static class ReflectUtil {
		/**
		 * 利用反射获取指定对象的指定属性
		 * 
		 * @param obj
		 *            目标对象
		 * @param fieldName
		 *            目标属性
		 * @return 目标属性的值
		 */
		public static Object getFieldValue(Object obj, String fieldName) {
			Object result = null;
			Field field = ReflectUtil.getField(obj, fieldName);
			if (field != null) {
				field.setAccessible(true);
				try {
					result = field.get(obj);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return result;
		}

		/**
		 * 利用反射获取指定对象里面的指定属性
		 * 
		 * @param obj
		 *            目标对象
		 * @param fieldName
		 *            目标属性
		 * @return 目标字段
		 */
		private static Field getField(Object obj, String fieldName) {
			Field field = null;
			for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz
					.getSuperclass()) {
				try {
					field = clazz.getDeclaredField(fieldName);
					break;
				} catch (NoSuchFieldException e) {
					// 杩欓噷涓嶇敤鍋氬鐞嗭紝瀛愮被娌℃湁璇ュ瓧娈靛彲鑳藉搴旂殑鐖剁被鏈夛紝閮芥病鏈夊氨杩斿洖null銆�
				}
			}
			return field;
		}

		/**
		 * 利用反射设置指定对象的指定属性为指定的值
		 * 
		 * @param obj
		 *            目标对象
		 * @param fieldName
		 *            目标属性
		 * @param fieldValue
		 *            目标值
		 */
		public static void setFieldValue(Object obj, String fieldName,
				Object fieldValue) {
			Field field = ReflectUtil.getField(obj, fieldName);
			if (field != null) {
				try {
					field.setAccessible(true);
					field.set(obj, fieldValue);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static class FlipInfoNotSupportException extends RuntimeException {

		public FlipInfoNotSupportException() {
			super();
		}

		public FlipInfoNotSupportException(String message, Throwable cause) {
			super(message, cause);
		}

		public FlipInfoNotSupportException(String message) {
			super(message);
		}

		public FlipInfoNotSupportException(Throwable cause) {
			super(cause);
		}
	}

}