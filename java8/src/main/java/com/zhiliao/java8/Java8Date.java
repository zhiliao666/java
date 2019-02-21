package com.zhiliao.java8;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Java8Date {

	public static void main(String[] args) {
		// 获取当前的日期时间
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("当前时间: " + currentTime);
		System.out.println("年: " + currentTime.getYear()+",月："+currentTime.getMonth()
				+",DayOfMonth："+currentTime.getDayOfMonth()+",DayOfYear："+currentTime.getDayOfYear()
				+",getDayOfWeek："+currentTime.getDayOfWeek()
				+",hour："+currentTime.getHour()+",Nano："+currentTime.getNano());
		
		LocalDate date1 = currentTime.toLocalDate();
		System.out.println("date1: " + date1);
		Month month = currentTime.getMonth();
		DayOfWeek week = currentTime.getDayOfWeek();
		int day = currentTime.getDayOfMonth();
		int seconds = currentTime.getSecond();
		int year = currentTime.getYear();

		System.out.println("年: " + year +",月: " + month +",周: " + week.getValue() + ", 日: " + day + ", 秒: " + seconds);
		
		String format = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-w"));
		
		System.out.println("format-date: " + format);

		LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
		System.out.println("date2: " + date2);

		// 12 december 2014
		LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
		System.out.println("date3: " + date3);

		// 22 小时 15 分钟
		LocalTime date4 = LocalTime.of(22, 15);
		System.out.println("date4: " + date4);

		// 解析字符串
		LocalTime date5 = LocalTime.parse("20:15:30");
		System.out.println("date5: " + date5);
		
		Date d = new Date(1546312919000l);
		System.out.println("d: " +d);
		System.out.println("week: " + getYearWeekByDate(d));
		
		String format1 = LocalDate.of(2018, 12, 29).format(DateTimeFormatter.ofPattern("YYYYw"));
		System.out.println("week2: " + format1);
		
		
		testZonedDateTime();
	}
	
	public static void testZonedDateTime(){
	    
	      // 获取当前时间日期
	      ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
	      System.out.println("date1: " + date1);
	      
	      ZoneId id = ZoneId.of("America/Sao_Paulo");
	      System.out.println("ZoneId: " + id);
	      System.out.println("ZoneDate: " + date1.of(LocalDateTime.now(), id));
	      
	      
	      ZoneId id2 = ZoneId.of("Europe/Paris");
	      System.out.println("ZoneId: " + id2);
	      System.out.println("ZoneDate: " + date1.of(LocalDateTime.now(), id2));
	        
	      ZoneId currentZone = ZoneId.systemDefault();
	      System.out.println("当期时区: " + currentZone);
	      System.out.println("ZoneDate: " + date1.of(LocalDateTime.now(), currentZone));
	   }
	
	// java1.8之前
	static ThreadLocal<SimpleDateFormat> dateFormatyearweek = new ThreadLocal<SimpleDateFormat>();
	
	/**
	 * 存在跨年周数不统一bug   如2018-12-31  获取到的周是20181  2019-01-01获取到的是20191
	 * @param date
	 * @return
	 */
	public static String getYearWeekByDate(Date date) {
		SimpleDateFormat sdf = dateFormatyearweek.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat("yw");
			dateFormatyearweek.set(sdf);
			return sdf.format(date);
		}
		return sdf.format(date);
	}

}
