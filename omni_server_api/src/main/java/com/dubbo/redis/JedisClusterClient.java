package com.dubbo.redis;

import redis.clients.jedis.JedisPool;

import java.util.Map;

public interface JedisClusterClient extends JedisClient{

	Map<String, JedisPool> getClusterNodes();
}
