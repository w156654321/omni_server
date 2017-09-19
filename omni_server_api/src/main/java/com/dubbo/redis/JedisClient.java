package com.dubbo.redis;

public interface JedisClient {


	public String get(String key);
	
	public String set(String key, String value);
	
	public String hget(String hkey, String key);
	
	public long hset(String hkey, String key, String value);
	/**
	 * 自增
	 * @param key
	 * @return
	 */
	public long incr(String key);
	/**
	 * 设置有效时间
	 * @param key
	 * @param seconds
	 * @return
	 */
	public long expire(String key, int seconds);
	/**
	 * 查看是否存在
	 * @param key
	 * @return
	 */
	public long ttl(String key);
	
	public long del(String key);
	
	public long hdel(String hkey, String key);
}
