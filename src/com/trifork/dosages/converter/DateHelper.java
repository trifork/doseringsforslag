package com.trifork.dosages.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

	public static Date date(String s) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(s);
		} 
		catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
	
}
