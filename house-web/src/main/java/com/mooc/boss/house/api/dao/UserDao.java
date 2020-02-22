package com.mooc.boss.house.api.dao;

import com.mooc.boss.house.api.common.RestResponse;
import com.mooc.boss.house.api.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


@Repository
@DefaultProperties(groupKey = "userDao",
        commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")},
        threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "10")
                , @HystrixProperty(name = "maxQueueSize", value = "1000")},
        threadPoolKey = "userDao"
)
public class UserDao {
//    @Autowired
//    private GenericRestTemplate rest;
    @Value("${user.service.name}")
    private String userServiceName;
    @Value("${http.prefix}")
    private String httpPrefix;

    @Autowired
    private RestTemplate restTemplate;

    //降级方法
    public User getUserByTokenFb(String token) {
        return new User();
    }

    /**
     * 调用鉴权服务
     *
     * @param token
     * @return
     */
    @HystrixCommand(fallbackMethod = "getUserByTokenFb")
    public User getUserByToken(String token) {
        String url = "http://" + userServiceName + "/user/get?token=" + token;
        RestResponse<User> response =
                restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,
                        new ParameterizedTypeReference<RestResponse<User>>() {}).getBody();
        if (response.getCode() == 0) {
            return response.getResult();
        }
        //return null;
        throw new IllegalStateException("Can not auth user");
    }

    public <T> ResponseEntity<T> get(String url, ParameterizedTypeReference<T> responseType) {
        return restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, responseType);
    }



}
