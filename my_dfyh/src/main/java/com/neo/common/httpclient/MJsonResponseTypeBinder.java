package com.neo.common.httpclient;

import java.io.InputStream;

import com.neo.common.util.JsonUtils;

public class MJsonResponseTypeBinder implements MResponseTypeBinder {

	@Override
	public <T> T toObject(String content, Class<T> clazz) throws Exception {
		return JsonUtils.fromJson(content, clazz);
	}

	@Override
	public <T> T toObject(InputStream content, Class<T> clazz) throws Exception {
		return JsonUtils.fromJson(content, clazz);
	}

	@Override
	public <T> T toObject(byte[] content, Class<T> clazz) throws Exception {
		return JsonUtils.fromJson(content, clazz);
	}

}
