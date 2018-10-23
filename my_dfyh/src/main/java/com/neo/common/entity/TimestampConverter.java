package com.neo.common.entity;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.neo.framework.util.DateUtil;

public class TimestampConverter implements Converter<String, Timestamp> {

	@Override
	public Timestamp convert(String timeStr) {
		// TODO Auto-generated method stub
		Timestamp t = null;
		if (!StringUtils.isEmpty(timeStr)) {
			try {
				Date date = DateUtil.parse(timeStr, "yyyy-MM-dd");
				t = new Timestamp(date.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				// ignore it;
			}    
		}
		return t;
	}

}