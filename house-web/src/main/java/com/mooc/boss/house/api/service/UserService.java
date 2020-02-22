package com.mooc.boss.house.api.service;

import com.mooc.boss.house.api.feign.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserFeignService userFeignService;

    public String getUserName(Long id) {
        return userFeignService.getUserName(id).getResult();
    }
}
