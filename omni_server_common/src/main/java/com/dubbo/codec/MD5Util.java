package com.dubbo.codec;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * MD5工具类
 * 
 * @author wangweiping
 * 
 */
public abstract class MD5Util {

	private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

	private static ThreadLocal<MessageDigest> md5MessageDigestCache = new ThreadLocal<MessageDigest>() {
		@Override
		protected MessageDigest initialValue() {
			try {
				return MessageDigest.getInstance("md5");
			} catch (Exception ex) {
				logger.error("", ex);
			}
			return null;
		}
	};

	public static String md5(String str, String charset) {
		try {
			return bytes2Hex(md5(str.getBytes(charset)));
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return null;
	}

	public static String md5_16(String str) {
		String md5_32 = bytes2Hex(md5(str.getBytes()));
		if (StringUtils.isBlank(md5_32)) {
			return null;
		}
		return md5_32.substring(8, 24);
	}

	public static String md5(String str) {
		return bytes2Hex(md5(str.getBytes()));
	}

	/**
	 * MD5加密
	 * 
	 * @param bytes
	 * @return
	 */
	private static byte[] md5(byte[] bytes) {
		try {
			MessageDigest md5 = md5MessageDigestCache.get();
			md5.update(bytes);
			return md5.digest();
		} catch (Exception ex) {
		}
		return null;
	}

	/**
	 * 将字节数组转换为16进制字符串表示
	 * 
	 * @param bytes
	 *            待转换的字节数组
	 * @return 16进制表示的字符串
	 */
	private static String bytes2Hex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		String hex = null;
		for (int i = 0; i < bytes.length; i++) {
			hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sb.append("0");
			}
			sb.append(hex);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(md5("agentID=&type=&phone=&version=|||7A3A4A4283E62CB"));
	}
}
