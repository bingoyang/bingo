package com.visfull.web.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String,Date>{

	public Date convert(String source) {
		DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			if(source!=null&&!"".equals(source))
				date = dateTimeFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
