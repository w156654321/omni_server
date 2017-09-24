package com.dubbo.utils;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {

	public static String getId(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String getId(String sub){
		long nextLong = new Random().nextInt();
		nextLong = Math.abs(nextLong);
	    String strDate = sub+DateUtils.getStrDate("yyyymmddHHmmss"+nextLong);
		return strDate;
	}
	
	public static void main(String[] args) {
		System.out.println(UUIDUtil.getId());
	}
}
