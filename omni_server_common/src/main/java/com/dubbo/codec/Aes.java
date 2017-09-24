package com.dubbo.codec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * 
 * 名称：AES加解密
 * 
 * 创建者： 杨春龙
 * 创建时间：2016-08-09
 * 创建描述：AES加解密方法
 * 
 * 修改者： 李宗杰
 * 修改时间：2016-09-21
 * 修改描述： 1. 审查代码及按规范修改类，补充类说明信息
 *           2. 内部方法修改为private
 * 
 * 审核者： 
 * 审核时间：
 * 审核描述：
 *          
 */
public class Aes {
	
	/** 
	 * 加密 
	 *  
	 * @param content 需要加密的内容 
	 * @param password  加密密码 
	 * @return String
	 */	
	public static String encryptStr(final String content, final String password){
		byte[] encryptResult=encrypt(content, password);
		return parseByte2HexStr(encryptResult);
	}
		
	/** 
	 * 加密 
	 *  
	 * @param content 需要加密的内容 
	 * @param password  加密密码 
	 * @return byte[]
	 */  
	private static byte[] encrypt(String content, String password) {  
        try {                     	
        	KeyGenerator kgen = KeyGenerator.getInstance( "AES" );
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(password.getBytes());
            kgen.init(128,secureRandom);	            	            
            SecretKey secretKey = kgen.generateKey();  
            byte[] enCodeFormat = secretKey.getEncoded();  
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
            byte[] byteContent = content.getBytes("utf-8");  
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化   
            byte[] result = cipher.doFinal(byteContent);  
            return result; // 加密   
        } catch (Exception e) {  
                e.printStackTrace();  
        }
        return null;  
	}	
	
	/**解密 
	 * @param token  待解密内容  String
	 * @param password 解密密钥 
	 * @return String
	 */ 
	public static String decryptStr(final String token, final String password){
		byte[] decryptFrom = parseHexStr2Byte(token);  
		byte[] decryptResult = decrypt(decryptFrom, password);  
		return new String(decryptResult);
	} 
	
	/**解密 
	 * @param content  待解密内容  byte[]
	 * @param password 解密密钥 
	 * @return byte[]
	 */  
	private static byte[] decrypt(byte[] content, String password) {  
        try {  
        	KeyGenerator kgen = KeyGenerator.getInstance( "AES" );
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(password.getBytes());
            kgen.init(128,secureRandom);	  
            SecretKey secretKey = kgen.generateKey();  
            byte[] enCodeFormat = secretKey.getEncoded();  
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");              
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器   
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化   
            byte[] result = cipher.doFinal(content);  
            return result; // 加密   
        } catch (Exception e) {  
                e.printStackTrace();  
        }  
        return null;  
	}
	
	/**将二进制转换成16进制 
	 * @param buf 
	 * @return 
	 */  
	private static String parseByte2HexStr(byte buf[]) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < buf.length; i++) {  
                String hex = Integer.toHexString(buf[i] & 0xFF);  
                if (hex.length() == 1) {  
                        hex = '0' + hex;  
                }  
                sb.append(hex.toUpperCase());  
        }  
        return sb.toString();  
	}  
	
	/**将16进制转换为二进制 
	 * @param hexStr 
	 * @return 
	 */  
	private static byte[] parseHexStr2Byte(String hexStr) {  
        if (hexStr.length() < 1)  
                return null;  
        byte[] result = new byte[hexStr.length()/2];  
        for (int i = 0;i< hexStr.length()/2; i++) {  
                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
                result[i] = (byte) (high * 16 + low);  
        }  
        return result;  
	}
}