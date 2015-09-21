package com.wang.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {

	public static void main(String[] args) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
		Date date = new Date();
		String dateStr = dateFormat.format(date);
		try {
			Date result = dateFormat.parse(dateStr);
			System.out.println(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(dateStr);

	}

}
