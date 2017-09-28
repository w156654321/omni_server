/**
 * Copyright © since 2008. All Rights Reserved
 * 汇元银通（北京）在线支付技术有限公司   www.heepay.com
 */
package com.dubbo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 名称：序列化工具
 * <p>
 * 创建者
 * 创建时间
 * 创建描述：
 *
 * 审核者：
 * 审核时间：
 * 审核描述：
 *
 * 修改者：
 * 修改时间：
 * 修改内容：
 */
public class SerializeUtil {

    /**
     * 定义全局日志
     */
    private static final Logger logger = LogManager.getLogger();

    private SerializeUtil() {
    }

    /**
     * 序列化
     *
     * @param object    object
     * @return          二进制
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos;
        ByteArrayOutputStream baos;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (Exception e) {
            logger.error("序列化对象{}时发生错误。", object, e);
        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param bytes bytes
     * @return      object
     */
    public static Object deserialize(byte[] bytes) {
        ByteArrayInputStream bais;
        try {
            if(null == bytes){
                return null;
            }
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            logger.error("反序列化{}时发生错误。", bytes, e);
        }
        return null;
    }
}