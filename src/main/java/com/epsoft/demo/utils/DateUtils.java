package com.epsoft.demo.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.boot.SpringApplication;

public class DateUtils {

	public static final String DATE_TIME_FORMATE = "yyyy-MM-dd HH:mm:ss";

	public static Date stringToDate(String date,String dateFormate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
		Date time = sdf.parse(date);
		return time;
		
	}

	public static String getYouWantTime(String formatTime, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatTime);
		String stringTime = sdf.format(date);
		return stringTime;
	}
	
	public static long until(LocalDate startDate, LocalDate endDate){
	        return startDate.until(endDate, ChronoUnit.DAYS);    
	 }
	
	public static void main(String[] args) throws Exception {
		Date d1 = DateUtils.stringToDate("2019-09-22 12:11:11", DateUtils.DATE_TIME_FORMATE);
		System.out.println(d1);
		Date d2 = DateUtils.stringToDate("2019-08-22 12:11:11", DateUtils.DATE_TIME_FORMATE);
		System.out.println(d2);
		int compareTo = d1.compareTo(d2);
		System.out.println(compareTo);
	}
}
