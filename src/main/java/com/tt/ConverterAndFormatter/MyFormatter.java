package com.tt.ConverterAndFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class MyFormatter implements Formatter<Date> {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String print(Date object, Locale locale) {
		return dateFormat.format(object);
	}

	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		return dateFormat.parse(text); // Formatter只能对字符串转换
	}

}
