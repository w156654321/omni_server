package com.dubbo.codec;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

public class TokenUtils {
	public static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
			'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'0', '1', '2', '3','4', '5', '6', '7', '8', '9' };
	public static final int codeCount = 4;
	
	/**
	 * 生成邀请码
	 * @author fanjunzeng  
	 * @date 2016年2月2日 下午8:12:50 
	 * @param p 前缀
	 * @return String
	 * @throws
	 */
	public static String getToken(String p){
		StringBuffer randomCode = new StringBuffer();
		if(StringUtils.isNotBlank(p)){
			randomCode.append(p);
		}
		for (int i = 0; i < codeCount; i++) {
			// 得到随机产生的验证码数字。
			String code = String.valueOf(codeSequence[RandomUtils.nextInt(0,36)]);

			// 将产生的四个随机数组合在一起。
			randomCode.append(code);
		}
		return randomCode.toString();
	}
	
	public static void main(String[] args) {
		
		System.out.println(getToken("R"));
		
	}
}
