package com.mooc.boss.house.api.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Configuration
public class HttpClientPool {
    @Autowired
    private HttpClientProperties httpPoolProperties;

    @Bean
    public HttpClient httpClient(){
        System.out.println("init feign httpclient configuration " );
        // 生成默认请求配置
        RequestConfig requestConfig = RequestConfig.custom()
                //服务器返回数据(response)的时间，超过抛出read timeout
                .setSocketTimeout(httpPoolProperties.getSocketTimeout())
                //连接上服务器(握手成功)的时间，超出抛出connect timeout
                .setConnectTimeout(httpPoolProperties.getConnectTimeout())
                //从连接池中获取连接的超时时间，超时间未拿到可用连接，会抛出ConnectionPoolTimeoutException
                .setConnectionRequestTimeout(httpPoolProperties.getConnectionRequestTimeout())
                .build();

        // 连接池配置
        // 长连接保持30秒
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(30, TimeUnit.MILLISECONDS);
        connectionManager.setMaxTotal(httpPoolProperties.getMaxTotal());// 总连接数
        connectionManager.setDefaultMaxPerRoute(httpPoolProperties.getDefaultMaxPerRoute());// 同路由的并发数
        connectionManager.setValidateAfterInactivity(httpPoolProperties.getValidateAfterInactivity());

        // httpclient 配置
        HttpClient client
                = HttpClientBuilder.create()
                    // 保持长连接配置，需要在头添加Keep-Alive
                    .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                    .setDefaultRequestConfig(requestConfig)//设置requestConfig
                    .setUserAgent(httpPoolProperties.getAgent())//设置User-Agent
                    .setMaxConnPerRoute(httpPoolProperties.getMaxConnPerRoute())//设置一个远端IP最大的连接数
                    .setMaxConnTotal(httpPoolProperties.getMaxConnTotaol())//设置总的连接数
                    //不重用连接，默认是重用，建议保持默认开启连接池，节省建立连接开销
    				//.setConnectionReuseStrategy(new NoConnectionReuseStrategy())
                    .setConnectionManager(connectionManager)
                    .build();

        // 启动定时器，定时回收过期的连接
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //        System.out.println("=====closeIdleConnections===");
                connectionManager.closeExpiredConnections();
                connectionManager.closeIdleConnections(5, TimeUnit.SECONDS);
            }
        }, 10 * 1000, 5 * 1000);
        System.out.println("===== Apache httpclient 初始化连接池===");

        return client;
    }


}