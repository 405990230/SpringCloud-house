package com.mooc.boss.house.comment.dao;

import com.mooc.boss.house.comment.common.RestResponse;
import com.mooc.boss.house.comment.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallBack implements UserFeignService {
    @Override
    public RestResponse<User> getUserDetailById(Long id) {
        return null;
    }
}
