package com.mooc.boss.house.api.hystrix;

import com.mooc.boss.house.api.feign.UserFeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFeignFallBackFactory implements FallbackFactory<UserFeignService> {
    @Override
    public UserFeignService create(Throwable throwable) {
        return new UserFeignServiceHystrix();
    }
}
