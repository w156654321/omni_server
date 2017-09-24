package com.dubbo.codec;

/**
 * 
 * 名称：Base64加解密
 * 
 * 创建者： 侯建春
 * 创建时间：2016-07-14
 * 创建描述：Base64加解密
 * 
 * 修改者： 李宗杰
 * 修改时间：2016-09-21
 * 修改描述： 1. 审查代码及按规范修改类，补充类说明信息
 *           
 * 审核者： 
 * 审核时间：
 * 审核描述：
 *          
 */
public class Base64{
    /**
	 * Base64编码加密
	 *
	 * @param data 待加密字节数组
	 * @return 加密后字符串
	 */
    public static String encode(byte[] data){
        if (data == null) return null;
        return new String(org.bouncycastle.util.encoders.Base64.encode(data));
    }

    /**
     * Base64编码解密
     *
     * @param data 待解密字符串
     * @return 解密后字节数组
     * @throws CodecException 异常
     */
    public static byte[] decode(String data) throws CodecException{
        if (data == null) return null;
        try{
            return org.bouncycastle.util.encoders.Base64.decode(data.getBytes());
        } catch (RuntimeException e){
            throw new CodecException(e.getMessage(), e);
        }
    }

}