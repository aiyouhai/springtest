package com.tt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig {
	@Value("${redis.pool.maxTotal}")
	private Integer maxTotal;
	@Value("${redis.pool.maxIdle}")
	private Integer maxIdle;
	@Value("${redis.pool.maxWait}")
	private Integer maxWaitMillis;
	@Value("${redis.pool.testOnBorrow}")
	private Boolean testOnBorrow;
	@Value("${redis.host}")
	private String host;
	@Value("${redis.port}")
	private Integer port;

	@Bean(name = "jedisPoolConfig")
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(maxTotal);
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		return jedisPoolConfig;
	}

	@Bean(name = "jedisConnectionFactory")
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(host);
		jedisConnectionFactory.setPort(port);
		jedisConnectionFactory.setPoolConfig(jedisPoolConfig());
		return jedisConnectionFactory;
	}

	@Bean
	public RedisTemplate<?, ?> redisTemplate() {
		RedisTemplate<byte[], byte[]> template = new RedisTemplate<byte[], byte[]>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
	/*
	 * @Bean public RedisTemplate<String, Object>
	 * redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
	 * RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
	 * redisTemplate.setConnectionFactory(jedisConnectionFactory);
	 * 
	 * // 使用Jackson2JsonRedisSerialize 替换默认序列化 Jackson2JsonRedisSerializer
	 * jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
	 * 
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * objectMapper.setVisibility(PropertyAccessor.ALL,
	 * JsonAutoDetect.Visibility.ANY);
	 * objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	 * 
	 * jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
	 * 
	 * // 设置String类型的序列化规则和 key的序列化规则
	 * redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
	 * redisTemplate.setKeySerializer(new StringRedisSerializer());
	 * 
	 * // 设置Hash类型的序列化规则和 key的序列化规则
	 * redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
	 * redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
	 * 
	 * redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
	 * redisTemplate.setEnableDefaultSerializer(true);
	 * redisTemplate.afterPropertiesSet(); return redisTemplate; }
	 */

}
