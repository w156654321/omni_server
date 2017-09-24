package com.dubbo.enums;

public enum WeChatTradeType {
	
    JSAPI("JSAPI","公众号支付"),
    NATIVE("NATIVE","扫码支付"),
    APPPAY("APP","APP支付");

	String _value; 
	
	/**
	 * 存放内容
	 */
	String _Content;
	
	/**
	 * 私有构造函数
	 * @param value 枚举值
	 * @param content 缓存内容
	 * @return 
	 */
	WeChatTradeType(String value, String content) {  
		this._value = value;  
		this._Content = content;  
	}  
	
	/**
	 * 取得枚举对象值
	 * @return 枚举对象值
	 */
	public String getValue() {
		return this._value;
	}
	
	/**
	 * 取得内容
	 * @return 内容
	 */
	public String getContent() {
		return this._Content;
	}
	
	
	public static String getContentByValue(String value){
		WeChatTradeType[] enums = WeChatTradeType.values();
		for(WeChatTradeType e : enums){
			if(e.getValue().equals(value)){
				return e.getContent();
			}
		}
		return "";
	}
	
}
