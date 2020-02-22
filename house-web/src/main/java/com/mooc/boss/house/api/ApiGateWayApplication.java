package com.mooc.boss.house.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
public class ApiGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayApplication.class, args);
    }

//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @RequestMapping("index1")
//    @ResponseBody
//    public List<ServiceInstance> getReister() {
//        return discoveryClient.getInstances("user");
//    }
}
