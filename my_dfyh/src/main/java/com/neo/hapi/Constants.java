package com.neo.hapi;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Constants {

	public static final String USERCONTEXT = "USERCONTEXT";

	public static final String DEFAULT_DATASOURCE_ID = "dataSource";

	public static final String ALI_OSS_IMAGE_HOST = "";

	public static final String THREAD_CONTEXT_SESSION_KEY = "THREAD_CONTEXT_SESSION_KEY";
	public static final String THREAD_CONTEXT_REQUEST_KEY = "THREAD_CONTEXT_REQUEST_KEY";
	public static final String THREAD_CONTEXT_RESPONSE_KEY = "THREAD_CONTEXT_RESPONSE_KEY";

	private static Map<String, Properties> propmaps = new HashMap<String, Properties>();
	
	public static final String QINIU_DOWNLOAD_HOST = getProperty("hongtu.file.url");
	public static final String FILE_UPLOAD_DIR = getProperty("hongtu.fileupload.dir");

	public static String getProperty(String key) {
		return getProperties("config.properties").getProperty(key);
	}

	private static Properties getProperties(String propsName) {
		Properties properties = propmaps.get(propsName);
		if (properties == null) {
			properties = new Properties();
			try {
				InputStream inputStream = Constants.class.getClassLoader()
						.getResourceAsStream(propsName);

				properties.load(inputStream);
				propmaps.put(propsName, properties);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return properties;
	}
}
