package com.gr.grquickrescue.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtility {

	public static Date toDate(String date) {
		Date convertedDate = null;	
		try {
				convertedDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			} catch (Exception e) {
					e.printStackTrace();
			}
			return convertedDate;
	}
	
}
