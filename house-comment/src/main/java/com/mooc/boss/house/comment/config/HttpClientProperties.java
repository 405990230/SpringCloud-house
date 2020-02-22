package com.mooc.boss.house.comment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "http.pool.conn")
@Data
public class HttpClientProperties {
    private String agent = "agent";
    private Integer maxTotal;
    private Integer defaultMaxPerRoute;
    private Integer connectTimeout;
    private Integer connectionRequestTimeout;
    private Integer socketTimeout;
    private Integer validateAfterInactivity;
    private Integer maxConnPerRoute;
    private Integer maxConnTotaol;

}
