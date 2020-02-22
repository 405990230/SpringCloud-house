package com.mooc.boss.house.api.feign;

import com.mooc.boss.house.api.common.RestResponse;
import com.mooc.boss.house.api.hystrix.UserFeignFallBackFactory;
import com.mooc.boss.house.api.model.Agency;
import com.mooc.boss.house.api.model.ListResponse;
import com.mooc.boss.house.api.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "HOUSE-USER",fallbackFactory = UserFeignFallBackFactory.class)
public interface UserFeignService {

    @RequestMapping("/getusername")
    RestResponse<String> getUserName(@RequestParam("id") Long id);

    @RequestMapping("/user/getList")
    RestResponse<List<User>> getUserList(@RequestBody User query);

    @RequestMapping("/user/add")
    RestResponse<User> addUser(@RequestBody User account);
    /**
     * 用户激活
     *
     * @param key
     */
    @RequestMapping("/user/enable")
    RestResponse<User> enable(@RequestParam("key") String key);

    @RequestMapping("/user/auth")
    RestResponse<User> authUser(@RequestBody User user);

    @RequestMapping("/user/logout")
    void logout(@RequestParam("token") String token);

    /**
     * 调用鉴权服务
     *
     * @param token
     * @return
     */
    @RequestMapping("/user/get")
    RestResponse<User> getUserByToken(@RequestParam("token") String token);

    @RequestMapping("/agency/list")
    RestResponse<List<Agency>> getAllAgency();

    @RequestMapping("/user/update")
    RestResponse<User> updateUser(@RequestBody User user);

    @RequestMapping("/agency/agentDetail")
    RestResponse<User> getAgentById(@RequestParam("id") Long id);

    @RequestMapping("/agency/agencyDetail")
    RestResponse<Agency> getAgencyById(@RequestParam("id") Integer id);

    @RequestMapping("/agency/add")
    void addAgency(@RequestBody Agency agency);

    @RequestMapping("/agency/agentList")
    RestResponse<ListResponse<User>> getAgentList(@RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset);

    @RequestMapping("/user/getKeyEmail")
    RestResponse<String> getEmail(@RequestParam("key") String key);

    @RequestMapping("/user/reset")
    RestResponse<User> reset(@RequestParam("key") String key, @RequestParam("password") String password);

    @RequestMapping("/user/resetNotify")
    void resetNotify(@RequestParam("email") String email, @RequestParam("url") String url);

}
