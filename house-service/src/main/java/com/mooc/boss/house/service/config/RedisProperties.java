package com.mooc.boss.house.service.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@Component
@Slf4j
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private static final String PROPERTIES_FILE = "application.properties";

    /**
     * spring.redis.database=0
     * spring.redis.host=192.168.33.200
     * spring.redis.port=6379
     * spring.redis.ssl=false
     * spring.redis.password=123456
     * spring.redis.connTimeout=5000ms
     * spring.redis.maxActive=500
     * spring.redis.maxIdle=10
     * spring.redis.minIdle=0
     * spring.redis.maxWait=5000ms
     * */
    private Integer database;
    private String host;
    private Integer port;
    private Boolean ssl;
    private String password;
    private Long connTimeout;
    private Integer maxActive;
    private Integer maxIdle;
    private Integer minIdle;
    private Integer maxWait;
    private String redisConnectString;

}