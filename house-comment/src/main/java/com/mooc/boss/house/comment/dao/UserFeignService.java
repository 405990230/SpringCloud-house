package com.mooc.boss.house.comment.dao;

import com.mooc.boss.house.comment.common.RestResponse;
import com.mooc.boss.house.comment.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "HOUSE-USER",fallbackFactory = UserFeignFallBackFactory.class)
public interface UserFeignService {
    @RequestMapping(value="/user/getById")
    RestResponse<User> getUserDetailById(@RequestParam("id")Long id);
}
