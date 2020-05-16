package com.epsoft.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
//nested exception is java.lang.NoSuchMethodError nettyjar包冲突
@Configuration
public class RedissonConfig {


    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

   /* @Value("${spring.redis.password}")
    private String password;
*/
    @Bean
    public RedissonClient redissonClient() throws IOException {
        Config config = new Config();
        //config.useSingleServer().setAddress("redis://"+"192.168.96.133"+":"+7002).setDatabase(0);
        config.useSingleServer().setAddress("redis://"+host+":"+port).setDatabase(0);
        RedissonClient redisson = Redisson.create(config);
        config.useSingleServer().setConnectionMinimumIdleSize(10);
        System.out.println(redisson.getConfig().toJSON().toString());
        return redisson;
    }
}
