package com.dubbo.mq;

/** mq
 * Created by liudh on 2017/9/20.
 */
public interface MqProducer {

     void sendQueue(String exchange_key, String queue_key, Object object);

}
