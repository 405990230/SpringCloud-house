package com.mooc.boss.house.comment.dao;

import com.mooc.boss.house.comment.common.RestResponse;
import com.mooc.boss.house.comment.model.*;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFeignFallBackFactory implements FallbackFactory<UserFeignService> {
    @Override
    public UserFeignService create(Throwable throwable) {
        return new UserFeignService(){
            @Override
            public RestResponse<User> getUserDetailById(Long id) {
                throw new RuntimeException(throwable.getMessage());
            }

        };
    }
}
