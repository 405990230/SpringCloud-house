package com.mooc.boss.house.api.controller;

import com.mooc.boss.house.api.common.RestResponse;
import com.mooc.boss.house.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiUserController {

    @Autowired
    private UserService userService;

    @RequestMapping("test/getusername")
    public RestResponse<String> getUserName(Long id) {
        log.info("Incoming request");
        return RestResponse.success(userService.getUserName(id));
    }
}
