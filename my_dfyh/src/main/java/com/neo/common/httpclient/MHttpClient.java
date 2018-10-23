package com.neo.common.httpclient;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import com.neo.common.httpclient.except.MHttpClientException;

/**
 * Http client
 * 
 * @author xiangf
 * @since JDK5.0
 */
public class MHttpClient {

	private HttpClient httpClient;
	private MResponseTypeBinder binder;
	private String url;
	private static Log log = LogFactory.getLog(MHttpClient.class);

	public MHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public <T> T get(Map<String, String> param)throws Exception {
		return get(param, null);
	}
	/**
	 * 发送Http Get请求
	 * 
	 * @param param
	 *            参数
	 * @param clazz
	 *            返回类型
	 * @return 请求结果
	 * @throws Exception
	 */
	public <T> T get(Map<String, String> param, Class<T> clazz)
			throws Exception {
		if (binder == null) {
			throw new MHttpClientException("Http client: Empty response binder");
		}
		if (BaseTypeUtil.isEmptyString(url)) {
			throw new MHttpClientException("Http client: URL response binder");
		}
		StringBuffer cUrl = new StringBuffer(url);
		if (param != null && param.size() > 0) {
			cUrl.append("?");
			Iterator<Entry<String, String>> it = param.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, String> entry = (Entry<String, String>) it.next();
				cUrl.append(entry.getKey());
				cUrl.append("=");
				cUrl.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				if (it.hasNext()) {
					cUrl.append("&");
				}
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("Request:" + cUrl.toString());
		}
		HttpGet httpget = new HttpGet(cUrl.toString());
		T t = null;
		InputStream is = null;
		try {
			HttpResponse response = httpClient.execute(httpget);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK
					&& response.getStatusLine().getStatusCode() != HttpStatus.SC_ACCEPTED
					&& response.getStatusLine().getStatusCode() != HttpStatus.SC_CREATED
					&& response.getStatusLine().getStatusCode() != HttpStatus.SC_CONTINUE) {
				throw new MHttpClientException(
						"Http client: Illegal http status:"
								+ response.getStatusLine().getStatusCode(),
						response.getStatusLine().getStatusCode());
			}
			HttpEntity entity = response.getEntity();
			if (entity != null && clazz != null) {
				if (log.isDebugEnabled()) {
					String content = EntityUtils.toString(entity, "UTF-8");
					log.debug("Response:" + content);
					t = binder.toObject(content, clazz);
				} else {
					is = entity.getContent();
					if (binder != null) {
						t = binder.toObject(is, clazz);
					}
				}
			}

		} catch (Exception e) {
			httpget.abort();
			throw e;
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return t;
	}

	/**
	 * 暂未实现
	 * 
	 * @param param
	 * @param clazz
	 * @return
	 */
	public <T> T post(Map<String, String> param, Class<T> clazz) {
		// 未实现
		return null;
	}

	/**
	 * 暂未实现
	 * 
	 * @param param
	 */
	public void post(Map<String, String> param) {

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MResponseTypeBinder getBinder() {
		return binder;
	}

	public void setBinder(MResponseTypeBinder binder) {
		this.binder = binder;
	}

}
