package com.dubbo.utils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @comment 字符串工具类
 */
public class StringUtil {
	 /**
     * 字符串分隔符
     */
    public static final String SEPARATOR = String.valueOf((char) 29);

	/**
	 * 首字母变小写
	 */
	public static String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] += ('a' - 'A');
			return new String(arr);
		}
		return str;
	}

	/**
	 * 首字母变大写
	 */
	public static String firstCharToUpperCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'a' && firstChar <= 'z') {
			char[] arr = str.toCharArray();
			arr[0] -= ('a' - 'A');
			return new String(arr);
		}
		return str;
	}
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj){
		if(obj == null)
			return "";
		else
			 return obj.toString();
	}

	/**
	 * 字符串为 null 或者为 "" 时返回 true
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim()) || "null".equals(str)? true : false;
	}

	/**
	 * 字符串不为 null 而且不为 "" 时返回 true
	 */
	public static boolean notBlank(String str) {
		return str == null || "".equals(str.trim()) || "null".equals(str) ? false : true;
	}

	public static boolean notBlank(String... strings) {
		if (strings == null)
			return false;
		for (String str : strings)
			if (str == null || "".equals(str.trim()))
				return false;
		return true;
	}

	public static boolean notNull(Object... paras) {
		if (paras == null)
			return false;
		for (Object obj : paras)
			if (obj == null)
				return false;
		return true;
	}

	/**
	 * 替换固定格式的字符串（支持正则表达式）
	 */
	public static String replaceAll(String str, String regex, String replacement) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, replacement);
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 将字符串首字母大写
	 */
	public static String firstToUpper(String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	/**
	 * 将字符串首字母小写
	 */
	public static String firstToLower(String str) {
		return Character.toLowerCase(str.charAt(0)) + str.substring(1);
	}


	/**
	 * 转为全部小写
	 */
	public static String toLower(String str) {
		str = str.trim().toLowerCase();
		return str;
	}
	
	public static String cast(String[] arr){
		if(arr==null) return null;
		int len=arr.length;
		StringBuffer sb=new StringBuffer();
		sb.append("(");
		for (int i = 0; i < len; i++) {
			String s=arr[i];
			if(isBlank(s)) continue;
			sb.append(s);
			if(i!=(len-1))
			sb.append(",");
		}
		sb.append(")");
		return sb.toString();
	}
	
	
	  
	/**     
	* @discription 前6后4获取短卡号
	* @created 2016年9月7日 下午8:56:45
	* @param cardNo
	* @return     
	*/
	public static String getShortCardNo(String cardNo){
		String shortCardNo;
		try{
			shortCardNo = cardNo.substring(0, 6) + cardNo.substring(cardNo.length()-4, cardNo.length());
		} catch(NullPointerException | IndexOutOfBoundsException e){
			return null;
		}
		
		return shortCardNo;
	}
	
	/**     
	* @discription 脱敏银行卡号
	* @created 2016年9月7日 下午8:56:45
	* @param cardNo
	* @return     
	*/
	public static String getEncryptedCardNo(String cardNo){
		String encryptedCardNo;
		try{
			String stars = cardNo.substring(6, cardNo.length()-4).replaceAll(".", "*");
			encryptedCardNo = cardNo.substring(0, 6) + stars + cardNo.substring(cardNo.length()-4, cardNo.length());
		} catch(NullPointerException | IndexOutOfBoundsException e){
			return null;
		}
		return encryptedCardNo;
	}
	
	
	  
	/**     
	* @description 姓名脱敏
	* @created 2016年10月26日 上午10:57:36
	* @param name
	* @return     
	*/
	public static String getEncryptedName(String name){
		String encrytedName;
		try{
			String stars = name.substring(1, name.length()).replaceAll(".", "某");
			encrytedName = name.substring(0, 1) + stars;
		} catch(Exception e){
			return null;
		}
		return encrytedName;
	}
	
	  
	/**     
	* @description 身份证号脱敏
	* @created 2016年10月26日 上午10:57:50
	* @return
	*/
	public static String getEncryptedIdcard(String certId){
		String encrytedId;
		try{
			String stars = certId.substring(7, 13).replaceAll(".", "*");
			encrytedId = certId.substring(0, 6) + stars + certId.substring(14, certId.length());
		} catch(Exception e){
			return null;
		}
		return encrytedId;
	}
	
	  
	/**     
	* @description 手机号脱敏
	* @created 2016年10月26日 上午10:58:09
	* @return
	*/
	public static String getEncryptedMobile(String mobileNo){
		String encrytedMobileNo;
		try{
			String stars = mobileNo.substring(0, 7).replaceAll(".", "*");
			encrytedMobileNo = stars + mobileNo.substring(7, 11);
		} catch(Exception e){
			return null;
		}
		return encrytedMobileNo;
	}
	
	/** 
	 * 金额格式化 
	 * @param amount 金额 
	 * @param flag 是否加￥符号
	 * @return 格式后的金额 
	 */
	public static String moneyFmt(String amount, boolean flag) { 
	    if (amount == null || amount.length() < 1) {
	        if(flag){
	          return ""; 
	        }else {
	          return "0"; 
	        }
	    } 
	    double num = Double.parseDouble(amount); 
	    DecimalFormat formater = new DecimalFormat("###,##0.00"); 
	    if(flag){
	    	return "￥" + formater.format(num);
	    } else {
	    	return formater.format(num);
	    }
	} 
	
	public static String moneyFmt(String amount){
		return moneyFmt(amount, true);
	}
	
	 /** 
	 * 金额去掉“,” 和 “￥”
	 * @param amount 金额 
	 * @return 去掉“,”后的金额 
	 */
	public static String unMoneyFmt(String amount) { 
	    String formatString = ""; 
	    if (amount != null && amount.length() >= 1) { 
	        formatString = amount.replaceAll(",", "");
	        formatString = amount.replaceAll("￥", "");
	    } 
	 
	    return formatString; 
	} 
	
	/**     
	* @discription 检查某个字符串是否为null，如果是则返回""，不是则返回该字符串
	* @created 2016年9月19日 下午1:39:30
	* @param str
	* @return  str or ""
	*/
	public static String getStringNotNull(String str) {
		if(str == null){
			return "";
		} else {
			return str;
		}
	}

	/**
	 * @discription 数组转字符串,以join符号分割
	 * @created 2016年10月26日 上午10:36:30
	 * @param join
	 * @param strAry
	 * @return
	 */
	public static String join(String join,String[] strAry){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<strAry.length;i++){
			if(i==(strAry.length-1)){
				sb.append(strAry[i]);
			}else{
				sb.append(strAry[i]).append(join);
			}
		}
		return new String(sb);
	}

	/**
	 * @discription 集合转字符串,以join符号分割
	 * @created 2016年10月26日 上午10:36:30
	 * @param join
	 * @param stringList
	 * @return
	 */
	public static String join(String join,List<String> stringList){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<stringList.size();i++){
			if(i==(stringList.size()-1)){
				sb.append(stringList.get(i));
			}else{
				sb.append(stringList.get(i)).append(join);
			}
		}
		return new String(sb);
	}



}
