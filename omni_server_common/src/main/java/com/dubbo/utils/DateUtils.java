package com.dubbo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils{
	/**   
	 * @Description: 获取String当前时间
	 * @author
	 * @date 2015年4月15日 下午3:03:20   
	*/
	public static String getStrDate(String dateFormat)
	{
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		String time = format.format(new Date());
		return time;
	}
	/**   
	 * @Description: yyyy-MM-dd HH:mm:ss 微信数据库所保存的格式
	 * @author
	 * @date 2015年4月15日 下午2:59:35   
	*/
	public static String getStrNowDate()
	{
		return getStrDate ("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 获取指定日期的字符串时间
	 * 2016-6-22
	 */
	public static String getStrDate(Date date ,String dateFormat){
		String str = "";
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			str = format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	 
}
