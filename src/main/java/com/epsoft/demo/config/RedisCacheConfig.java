package com.epsoft.demo.config;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport{
	
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheConfig.class);
	
	/*@Value("${spring.redis.host}")
	private String hostName;
	
	@Value("${spring.redis.port}")
	private Integer port;
	
	@Value("${spring.redis.password}")
	private String password;
	
	@Value("${spring.redis.timeout}")
	private int timeout;
	
	@Value("${spring.redis.database}")
	private int database;
	
	@Value("${jedis.pool.max-idle}")
	private int maxIdle;
	
	@Value("${jedis.pool.min-idle}")
	private int minIdle;*/
	
	/**
	   *  设置自动key的生成规则，配置spring boot的注解，进行方法级别的缓存
	   *  使用：进行分割，可以很多显示出层级关系
	  */
	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return (target,method,parames) ->{
			StringBuilder sb = new StringBuilder();
			sb.append(target.getClass().getName());
			sb.append(":");
			sb.append(method.getName());
			for (Object object : parames) {
				sb.append(String.valueOf(object));
			}
			String rsToUse = String.valueOf(sb);
			logger.info("自动生成Redis Key -> [{}]", rsToUse);
			return rsToUse;
		};
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		
		//初始化一个RedisCacheWriter
		RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
		RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
		
		// springboot2.0需要在缓存管理的时候对key和value进行序列化操作
        // springboot1.0可以不用下面3行
		RedisSerializer stringRedisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer serializer = this.getSerializer();
		RedisCacheConfiguration redisCacheConfiguration = defaultCacheConfig
		.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
		.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));
		
		//设置默认超过期时间是1000秒
		defaultCacheConfig.entryTtl(Duration.ofSeconds(1000));
		//初始化RedisCacheManager
		RedisCacheManager redisCacheManager = new RedisCacheManager(redisCacheWriter,redisCacheConfiguration);
		return redisCacheManager;
	}
	
	//序列化json防止乱码
	@SuppressWarnings("rawtypes")
	private Jackson2JsonRedisSerializer getSerializer() {
		// 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
		Jackson2JsonRedisSerializer js2j = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper obj = new ObjectMapper();
		 // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
		obj.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		// 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
		obj.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		js2j.setObjectMapper(obj);	
		return js2j;	
	}
	
	//RedisTemplate配置
	@SuppressWarnings("rawtypes")
	@Bean
	public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate redisTemplate = new StringRedisTemplate(factory);
		RedisSerializer stringRedisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer serializer = this.getSerializer();
		// 值采用json序列化
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
		redisTemplate.setKeySerializer(stringRedisSerializer);
		redisTemplate.setValueSerializer(serializer);
		
		// 设置hash key 和value序列化模式
		redisTemplate.setHashKeySerializer(stringRedisSerializer);
		redisTemplate.setHashValueSerializer(serializer);

		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
}