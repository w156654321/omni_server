package com.dubbo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


/**
 * 日期工具类
 * 
 * @author
 * 
 */
public class DateUtil {

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String formatDay(Date date) {
		return format(date, "yyyy-MM-dd");
	}
 
	
	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式
	 * @return 日期字符串
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return "null";
		}
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 日期加减運算
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}

	/**
	 * 将字符串转换为Date类型
	 *
	 * @param date
	 *            字符串类型
	 * @return 日期类型
	 */
	public static Date format(String date) {
		return format(date, null);
	}

	/**
	 * 将字符串转换为Date类型
	 *
	 * @param date
	 *            字符串类型
	 * @param pattern
	 *            格式
	 * @return 日期类型
	 */
	public static Date format(String date, String pattern) {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (date == null || date.equals("") || date.equals("null")) {
			return new Date();
		}
		Date d = null;
		try {
			d = new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException pe) {
		}
		return d;
	}
	
	  
    /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    } 
    
    /**  
     * 比较两个日期的先后
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static boolean compareDate(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        if(time1>time2){
        	return true;
        }
            
       return false;           
    }
    /**
     * 比较日期大小
     * 
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static boolean compareDate_2(Date smdate,Date bdate) throws ParseException
    {
        
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        if(time1>=time2){
            return true;
        }
            
       return false;           
    }

    public static String getDateDandom(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowDate = dateFormat.format(now);
        String str = "";
        Random ra =new Random();
        for (int i=0;i<5;i++){
            str = ra.nextInt(9)+1+"";
        }
        return nowDate+str;
    }


   /* @Test
    public void test(){
        try {
            System.out.println(compareDate_2(new Date(),new Date()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
}
