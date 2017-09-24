package com.dubbo.date;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * 
 * <p>Title: DateUtils.java</p>    
 * <p>Description: date相关工具类</p>     
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: firstdata</p>
 * @author W.X       
 * @version 1.0     
 * @created 2010-8-19 上午02:31:56
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils{
	
	static SimpleDateFormat df = new SimpleDateFormat();
	static{
		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
	}
	
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
	
	/**
	 * 格式化时间常量年-月-日
	 */
	public static final String DATE_FORMAT_DATE = "yyyy-MM-dd";
	
	/**
	 * 格式化时间常量年月日
	 */
	public static String DB_FORMAT_DATE = "yyyyMMdd";

	/**
	 * 格式化时间常量时：分：秒
	 */
	public static final String DATE_FORMAT_TIME = "HH:mm:ss";

	/**
	 * 格式化时间常量年-月-日 时：分：秒
	 */
	public static final String DATE_FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DATE_FORMAT_DATE_TIME_14 = "yyyyMMddHHmmss";
	/**
	 * 格式化时间常量年-月-日 时：分：秒(特定)
	 */
	public static final String DATE_FORMAT_DATE_TIME_START = "yyyy-MM-dd 00:00:00";
	/**
	 * 格式化时间常量年-月-日 时：分：秒(特定)
	 */
	public static final String DATE_FORMAT_DATE_TIME_END = "yyyy-MM-dd 23:59:59";
	
	/**
	 * 获取当年第一天
	 * 
	 * @author 彭阳 
	 * @version 1.0      
	 * @return
	 */
	public static Date getFristDayOfYear() {
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}
	
	/**
	 * 
	 * @description获取当前时间前一个小时的时间
	 * @author TianYanqing      
	 * @created 2017年4月27日 下午2:25:36     
	 * @param d
	 * @return
	 */
	public static Date getDatePerHour(Date d){
		Calendar calendar = Calendar.getInstance();
		if(d==null){
			d=new Date();
		}
		calendar.setTime(d);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		return calendar.getTime();
	}
	/**
	 * 
	 * @description获取前一个小时
	 * @author TianYanqing      
	 * @created 2017年4月27日 下午5:56:54     
	 * @param d
	 * @return
	 */
	public static int getHourOfPerHour(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
		
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 
	 * @description获取传入日期为周几
	 * @author TianYanqing      
	 * @created 2017年5月4日 下午5:43:21     
	 * @param date
	 * @return 返回1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六 
	 */
	public static int getDayofweek(Date date){  
		  if(date==null){
			  date = new Date();
		  }
		  Calendar cal = Calendar.getInstance();  
		  cal.setTime(date);  
		  return cal.get(Calendar.DAY_OF_WEEK);  
      }  
	
	/**
	 * 获取当年最后一天
	 * 
	 * @author 彭阳 
	 * @version 1.0      
	 * @return
	 */
	public static Date getLastDayOfYear() {
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		
		return calendar.getTime();
	}

	/**
	 * 得到系统当前日期中的年份
	 * 
	 * @return 年份
	 */
	public static int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 得到系统当前日期中的月份
	 * 
	 * @author yuanxing       
	 * @version 1.0 
	 * @return 月份
	 */
	public static int getMonthOfYear() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static Date getDate(String dateStr) {
        try {
            if (dateStr == null || dateStr.equals("")) {
                return null;
            }
            String DATE_FORMAT = "yyyyMMdd";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Date d = sdf.parse(dateStr);
            return d;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	/**
	 * 获取当前时间，只有年月日
	 *
	 * @author 彭阳
	 * @version 1.0
	 * @return
	 */
	public static Date getDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static String getDate(boolean flag) {
		return DateFormatUtils.format(new Date(), DATE_FORMAT_DATE);
	}

	/**
	 * 获取昨天日期，只有年月日
	 *
	 * @author 彭阳
	 * @version 1.0
	 * @return
	 */
	public static Date getYesterdayDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static Date getTomorrowDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 获取当前时间的下一天，只有年月日
	 *
	 * @author 彭阳
	 * @version 1.0
	 * @return
	 */
	public static Date getNextDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获取当前日期的前一天
	 * @param date
	 * @return
	 */
	public static Date getPreDate(Date date){
	  Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_MONTH, -1);
    date = calendar.getTime();
    return date;
	}

	/**
	 * 获取当前日期的前7天
	 * @param date
	 * @return
	 */
	public static Date getPre7Date(Date date){
	  Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_MONTH, -8);
    date = calendar.getTime();
    return date;
	}

	/**
	 * 得到系统当前日期中当前月的哪一天
	 *
	 * @author yuanxing
	 * @version 1.0
	 * @return 天份
	 */
	public static int getDayOfMonth() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到系统当前日期中当天的小时
	 *
	 * @author yuanxing
	 * @version 1.0
	 * @return 小时
	 */
	public static int getHourOfDay() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}



	/**
	 * 得到系统当前日期中当天的分钟
	 *
	 * @author yuanxing
	 * @version 1.0
	 * @return 分钟
	 */
	public static int getMiniteOfHour() {
		return Calendar.getInstance().get(Calendar.MINUTE);
	}

	/**
	 * 得到系统当前日期中当天的秒
	 *
	 * @author yuanxing
	 * @version 1.0
	 * @return 秒
	 */
	public static int getSecondOfMinite() {
		return Calendar.getInstance().get(Calendar.SECOND);
	}

	/**
	 * 获取日期d的days天后的一个Date
	 *
	 * @author yuanxing
	 * @version 1.0
	 * @param d
	 * @param days
	 * @return Date
	 */
	public static Date getInternalDateByDay(Date d, int days) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.DATE, days);
		return now.getTime();
	}

	/**
	 * 根据制定的时间转换字符串得到当前指定的时间格式 如：yyyy-MM-dd 23:59:59 得到时间为当前日期的最后一刻
	 * 返回一个指定的日期格式（yyyy-MM-dd HH:mm:ss）
	 *
	 * @author yuanxing
	 * @version 1.0
	 * @param returnDateStyle
	 * @param transFormatStyle
	 * @return dateStr
	 * @throws ParseException
	 */
	public static Date getSpecialDate(Date date, String returnDateStyle,
			String transFormatStyle) throws ParseException {
		Date dateStr = null;
		SimpleDateFormat sdf = null;
		SimpleDateFormat returnSdf = null;
		if ((returnDateStyle != null) && (!returnDateStyle.equals(""))
				&& (transFormatStyle != null) && (!transFormatStyle.equals(""))) {
			returnSdf = new SimpleDateFormat(returnDateStyle);
			sdf = new SimpleDateFormat(transFormatStyle);
			String dateString = sdf.format(date);
			Calendar myDate = Calendar.getInstance();
			myDate.setTime(returnSdf.parse(dateString));
			dateStr = myDate.getTime();
		}
		return dateStr;
	}

	/**
	 * 获得一个时间的格式化时间,如果时间为null,则格式化为当前时间
	 *
	 * */
	public static Date getFormatTime(Date date,String dateFormat){
		if(date == null)
			date = new Date();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT_DATE_TIME);
			String dateStr = sdf.format(date);
			Calendar returnDate = Calendar.getInstance();
			returnDate.setTime(sdf2.parse(dateStr));
			return returnDate.getTime();
		}catch(ParseException pe){
			return null;
		}
	}

	/**
	 * 把字符串转化为java.util.Date
	 *
	 * @author yuanxing
	 * @version 1.0
	 * @param strDate
	 * @throws ParseException
	 */
	public static Date getStrDate(String strDate) throws ParseException {
		Date date = null;
		if ((strDate != null) && (strDate != "")) {
			DateFormat df = DateFormat.getDateInstance();
			date = df.parse(strDate);
		}
		return date;
	}

	/**
	 * 把字符串转化为java.util.Date
	 *
	 * @author yuanxing
	 * @version 1.0
	 * @param strDate
	 * @throws ParseException
	 */
	public static Date getStr2Date(String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if((strDate != null) && (strDate != "")) {
				Date date = sdf.parse(strDate);
				return date;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 比较date1是否小于等于date2 yyyy-MM-dd类型
	 *
	 * @author yuanxing
	 * @version 1.0
	 * @param date1
	 * @param date2
	 * @return boolean
	 */
	public static boolean isLessEquals(Date date1, Date date2) {
		if (!date1.after(date2) && !date1.before(date2)) {
			return true;
		} else if (date1.before(date2))
			return true;
		else
			return false;
	}

	/**
	 * 比较date1是否大于等于date2 yyyy-MM-dd类型
	 *
	 * @author yuanxing
	 * @version 1.0
	 * @param date1
	 * @param date2
	 * @return boolean
	 */
	public static boolean isMoreEquals(Date date1, Date date2) {
		if (!date1.after(date2) && !date1.before(date2)) {
			return true;
		} else if (date1.after(date2))
			return true;
		else
			return false;
	}

	/**
	 * 传入日期和格式，返回字符串
	 *
	 * @author yuanxing
	 * @version 1.0
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getFormatDateDir(Date date, String format) {
		String DATE_FORMAT = DATE_FORMAT_DATE;
		if (format != null && !format.equals("")) {
			DATE_FORMAT = format;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(
				DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(date.getTime()));
	}

	/**
	 * 得到文件时间
	 *
	 * @author yuanxing
	 * @version 1.0
	 * @param filePatch
	 *            文件路径
	 * @return 最后修改时间
	 */
	public static String getFileDate(String filePatch) {
		String returnValue = "文件不存在！";
		if (filePatch != null) {
			filePatch = filePatch.replace("\\", "/");
			File file = new File(filePatch);
			if (file.exists() && file.isFile()) {
				Date date = new Date(file.lastModified());
				SimpleDateFormat simple = new SimpleDateFormat(
						DATE_FORMAT_DATE_TIME);
				return simple.format(date);
			}
		}
		return returnValue;
	}

	/**
	 * 得到当前时间的前/后若干月的时间 例如当前时间2006-05-16 间隔月数3月，则返回2006-02-16
	 *
	 * @author yuanxing
	 * @version 1.0
	 * @param months -
	 *            间隔月数
	 * @param dateFormat -
	 *            时间格式
	 * @return - 返回当时的时间
	 */
	public static Date getInternalTimeByMonth1(int months) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.add(Calendar.MONTH, months);
		return now.getTime();
	}

	/**
	 * 得到当前时间的前/后若干月的时间 例如当前时间2006-05-16 间隔月数3月，则返回2006-02-16
	 *
	 * @author yuanxing
	 * @version 1.0
	 * @param months -
	 *            间隔月数
	 * @param dateFormat -
	 *            时间格式
	 * @return - 返回当时的时间
	 */
	public static String getInternalTimeByMonth(int months, String dateFormat) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		SimpleDateFormat sdf = new SimpleDateFormat(
				dateFormat);
		sdf.setTimeZone(TimeZone.getDefault());
		now.add(Calendar.MONTH, months);
		return (sdf.format(now.getTime()));
	}

	/**
	 * 得到指定时间的前/后若干月的时间 例如指定时间2006-05-16 间隔月数3月，则返回2006-02-16
	 *
	 * @author yuanxing
	 * @version 1.0
	 * @param months -
	 *            间隔月数
	 * @param dateFormat -
	 *            时间格式
	 * @return - 返回当时的时间
	 */
	public static String getInternalTimeByMonth(Date date, int months,
			String dateFormat) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat(
				dateFormat);
		sdf.setTimeZone(TimeZone.getDefault());
		now.add(Calendar.MONTH, months);
		return (sdf.format(now.getTime()));
	}

	/**
	 * 把字符串转化为java.util.Date
	 * 
	 * @author yuanxing       
	 * @version 1.0 
	 * @param strDate
	 * @param dateFormat
	 * @throws ParseException
	 */
	public static Date getStrDate(String strDate, String dateFormat)
			throws ParseException {
		Date date = null;
		if ((strDate != null) && (strDate != "")) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			date = sdf.parse(strDate);
		}
		return date;
	}

	/**
	 * 把字符串转化为java.util.Date
	 * 
	 * @author yuanxing       
	 * @version 1.0 
	 * @param strDate
	 * @param dateFormat
	 * @throws ParseException
	 */
	public static Date getStrDate(Date strDate, String dateFormat)
			throws ParseException {
		Date date = null;
		if (strDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			date = getStrDate(sdf.format(strDate), dateFormat);
		}
		return date;
	}

	/**
	 * 把日期转化为日期字符串
	 * 
	 * @author yuanxing       
	 * @version 1.0 
	 * @param strDate
	 * @param dateFormat
	 * @throws ParseException
	 */
	public static String getDateStr(Date date, String dateFormat) {
		String strDate = null;
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			strDate = sdf.format(date);
		}
		return strDate;
	}
	
	/**
	 * 得到下number的年月，格式yyyy-MM
	 * 
	 * @author yuanxing       
	 * @version 1.0      
	 * @param number
	 * @return
	 */
	public static String getNextYearMonth(int number){
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		  /************得到月份*********/
		  //date = format.parse(yearMonth); //输入的年月字符串格式如：201002
		  Calendar calendar = Calendar.getInstance();
		 // calendar.setTime(date); 
		  calendar.add(Calendar.MONTH, number);  //number =1，则会获得下一个月份，number = -1 则会获得上一个月份,也可以输入：2，3，-2，-3等根据自己需要选择
		  return  format.format(calendar.getTime());  //获取月份
	}
	
	/**
	 * 日期转化类函数
	 * 
	 * @author yuanxing       
	 * @version 1.0      
	 * @param date
	 * @param patern
	 * @return
	 */
	public static String dateTodate(Date date, String patern) {
		String retval = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(patern);
			retval = sdf.format(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return retval;
	}
	
	/**
	 * 时间比大小 
	 * t1<t2=-1 t1=t2=0 t1>t2=1
	 * 
	 * @author yuanxing       
	 * @version 1.0      
	 * @param t1
	 * @param t2
	 * @param patern 格式化模板
	 * @return
	 */
    public static int timeCompare(String t1, String t2, String patern){   
        SimpleDateFormat formatter = new SimpleDateFormat(patern);   
        Calendar c1=Calendar.getInstance();   
        Calendar c2=Calendar.getInstance();   
        try {   
            c1.setTime(formatter.parse(t1));   
            c2.setTime(formatter.parse(t2));   
        } catch (ParseException e) {   
            e.printStackTrace();   
        }   
        int result=c1.compareTo(c2);   
        return result;   
    }   
    
    /**
     * 计算两个日期之间间隔的天数,t2需要大于t1
     * 
     * @author yuanxing       
     * @version 1.0      
     * @param t1
     * @param t2
     * @return
     * @throws ParseException
     */
    public static int getBetweenDays(String t1, String t2)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int betweenDays = 0;
		Date d1 = format.parse(t1);
		Date d2 = format.parse(t2);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第一个时间
		if (c1.after(c2)) {
			c1 = c2;
			c2.setTime(d1);
		}
		int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		betweenDays = c2.get(Calendar.DAY_OF_YEAR)
				- c1.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < betweenYears; i++) {
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
			betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
		}
		return betweenDays;
	}
    
    /**
     * 计算两个日期之间间隔的天数,d2需要大于d1
     * 
     * @author yuanxing       
     * @version 1.0      
     * @param d1
     * @param d2
     * @return
     * @throws ParseException
     */
    public static int getBetweenDays(Date d1, Date d2) {
		int betweenDays = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第一个时间
		if (c1.after(c2)) {
			c1 = c2;
			c2.setTime(d1);
		}
		int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		betweenDays = c2.get(Calendar.DAY_OF_YEAR)
				- c1.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < betweenYears; i++) {
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
			betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
		}
		return betweenDays;
	}
    
	static public String format(Date date,String pattern)
	{
		df.applyPattern(pattern);
		return df.format(date);
	}
	
	static public String format(long date,String pattern)
	{
		return format(new Date(date), pattern);
	}
	
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	 public static Date parseDate(Object str) throws ParseException {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
    
    /**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 获取当天的起始时间
	 * @param date
	 * @return
	 */
	public static Date startTime(Date date){
	  Calendar todayStart = Calendar.getInstance(); 
	  todayStart.set(Calendar.HOUR_OF_DAY, 0);
	  todayStart.set(Calendar.MINUTE, 0);
	  todayStart.set(Calendar.SECOND, 0);
    return todayStart.getTime();
	}
	
	/**
   * 获取当天的结束时间
   * @param date
   * @return
   */
  public static Date endTime(Date date){
    Calendar todayEnd = Calendar.getInstance(); 
    todayEnd.set(Calendar.HOUR_OF_DAY, 23);
    todayEnd.set(Calendar.MINUTE, 59);
    todayEnd.set(Calendar.SECOND, 59);
    return todayEnd.getTime();
  }
  
  /**
   * 
   * @方法说明：当前天数增加或减少
   * @时间：2017年2月17日 下午5:56:18
   * @创建人：wangdong
   */
  public static String getDayAdd(Date date,int count){
	  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
      Calendar c = Calendar.getInstance();
      c.add(Calendar.DAY_OF_MONTH, count);
      return sf.format(c.getTime());
  }

	/**
	 * 判断时间是否在时间段内
	 *
	 * @param strDate
	 *            当前时间 yyyy-MM-dd HH:mm:ss
	 * @param strDateBegin
	 *            开始时间 00:00:00
	 * @param strDateEnd
	 *            结束时间 00:05:00
	 * @return
	 */
	public static boolean isInDate(String strDate, String strDateBegin,
								   String strDateEnd) {
		// 截取当前时间时分秒
		int strDateH = Integer.parseInt(strDate.substring(11, 13));
		int strDateM = Integer.parseInt(strDate.substring(14, 16));
		int strDateS = Integer.parseInt(strDate.substring(17, 19));
		// 截取开始时间时分秒
		int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
		int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
		int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
		// 截取结束时间时分秒
		int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
		int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
		int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
		if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
			// 当前时间小时数在开始时间和结束时间小时数之间
			if (strDateH > strDateBeginH && strDateH < strDateEndH) {
				return true;
				// 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
			} else if (strDateH == strDateBeginH && strDateM >= strDateBeginM
					&& strDateM <= strDateEndM) {
				return true;
				// 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
			} else if (strDateH == strDateBeginH && strDateM == strDateBeginM
					&& strDateS >= strDateBeginS && strDateS <= strDateEndS) {
				return true;
			}
			// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
			else if (strDateH >= strDateBeginH && strDateH == strDateEndH
					&& strDateM <= strDateEndM) {
				return true;
				// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
			} else if (strDateH >= strDateBeginH && strDateH == strDateEndH
					&& strDateM == strDateEndM && strDateS <= strDateEndS) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @throws ParseException 
	 * @方法说明：获取上个月的第一天
	 * @时间：2017年3月10日 下午4:04:40
	 * @创建人：wangdong
	 */
	public static Date getPerFirstDayOfMonth(){
        SimpleDateFormat dft = new SimpleDateFormat(DATE_FORMAT_DATE_TIME_START);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        try {
			return getStrDate(dft.format(calendar.getTime()),DATE_FORMAT_DATE_TIME_START);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
        return new Date();
    }
	
	/**
	 * 
	 * @throws ParseException 
	 * @方法说明：获取上个月的最后一天
	 * @时间：2017年3月10日 下午4:04:13
	 * @创建人：wangdong
	 */
	public static Date getLastMaxMonthDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
	
	/**
	 * 
	 * @throws ParseException 
	 * @方法说明：获取当月第一天
	 * @时间：2017年3月10日 下午4:04:13
	 * @创建人：wangdong
	 */
	public static Date getMonthFirstDate(){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00"); 
			Calendar c = Calendar.getInstance();    
	        c.add(Calendar.MONTH, 0);
	        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	        String first = format.format(c.getTime());
			return getStr2Date(first);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
    }
	
	

	public static void main(String[] args) throws Exception{ 
/*		Date date = new Date();
		//System.out.println(date);
		date = getInternalDateByDay(date, 1);
		//System.out.println(date);
		String time = "092036";
		char c[] = time.toCharArray();
		time = c[0] + c[1] + ":" + c[2] + c[3] + ":" + c[4] + c[5];
		String s = "20010918" + " " + time;
		try {
			Date d = getStrDate(s, DB_FORMAT_DATE+ " " + DATE_FORMAT_TIME);
			System.out.println(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
/*		String date = "20111128";
		String time = "092249";
		char c[] = time.toCharArray();
		time = c[0] + "" + c[1] + ":" + c[2] + "" + c[3] + ":" + c[4] + "" + c[5];
		System.out.println(time);
		String dateformat = date + " " + time;
		System.out.println(dateformat);
		Date tradeDate = DateUtils.getStrDate(dateformat,  DateUtils.DB_FORMAT_DATE+ " " + DateUtils.DATE_FORMAT_TIME) ;
		System.out.println(tradeDate);*/
//		System.out.println(DateUtils.getDateStr(new Date(), "yyyy年MM月dd日 HH:mm:ss"));
//		System.out.println(DateUtils.getFormatDateDir(new Date(),DateUtils.DATE_FORMAT_DATE_TIME_START));
//		System.out.println(DateUtils.parseDate("2011-01-01","yyyy-MM-dd"));
//		System.out.println(DateUtils.format(null, "yyyyMMdd"));
        System.out.println(getMonthFirstDate());
	}
}
