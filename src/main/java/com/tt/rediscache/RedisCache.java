package com.tt.rediscache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.PostConstruct;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import com.tt.config.RedisConfig;

import redis.clients.jedis.exceptions.JedisConnectionException;

@Component

public class RedisCache implements Cache {
	private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

	private static JedisConnectionFactory jedisConnectionFactory;

	private String id;
	@Autowired
	private RedisConfig redisConfig;
	private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	// 从redisconfig中获取jedisConnectionFactory
	@PostConstruct
	public void initialize() {
		jedisConnectionFactory = redisConfig.jedisConnectionFactory();
	}

	public RedisCache(String id) {
		if (id == null) {
			throw new IllegalArgumentException("id is null");
		}
		this.id = id;
	}

	public RedisCache() {

	}

	public String getId() {

		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {

		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			connection.set(serializer.serialize(key), serializer.serialize(value));
			connection.lPush(serializer.serialize(id), serializer.serialize(key));
			System.out.println("写入缓存：" + key + "," + value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}

	}

	@Override
	public Object getObject(Object key) {
		// 先从缓存中去取数据,先加上读锁
		readWriteLock.readLock().lock();
		Object result = null;
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = serializer.deserialize(connection.get(serializer.serialize(key)));
			logger.info("命中mybaits二级缓存,value=" + result);

		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
			readWriteLock.readLock().unlock();
		}
		return result;
	}

	@Override
	public Object removeObject(Object key) {
		readWriteLock.writeLock().lock();

		RedisConnection connection = null;
		Object result = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = connection.expire(serializer.serialize(key), 0);
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
			readWriteLock.writeLock().unlock();
		}
		return result;
	}

	@Override
	public void clear() {
		readWriteLock.readLock().lock();
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			connection.flushDb();
			connection.flushAll();
			logger.debug("清除缓存.......");
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
			readWriteLock.readLock().unlock();
		}

	}

	@Override
	public int getSize() {
		int result = 0;
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			result = Integer.valueOf(connection.dbSize().toString());
			logger.info("添加mybaits二级缓存数量：" + result);
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		// TODO Auto-generated method stub
		return readWriteLock;
	}

	public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
		RedisCache.jedisConnectionFactory = jedisConnectionFactory;
	}
}
