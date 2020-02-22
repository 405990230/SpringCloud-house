package com.mooc.boss.house.user.controller;

import com.mooc.boss.house.user.common.RestResponse;
import com.mooc.boss.house.user.exception.IllegalParamsException;
import com.mooc.boss.house.user.model.User;
import com.mooc.boss.house.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("user")
public class UserController {


    //测试代码
    @Value("${server.port}")
    private Integer port;

    @RequestMapping("/getusername")
    public RestResponse<String> getUserName(Long id) {
        log.info("Request is coming,and port is " + port);
        if (id == null) {
            throw new IllegalParamsException(IllegalParamsException.Type.WRONG_PAGE_NUM, "错误分页");
        }
        return RestResponse.success("test-username " + port);
    }

    @Autowired
    private UserService userService;
    //-------------------查询---------------------

    @RequestMapping("getById")
    public RestResponse<User> getUserById(Long id) {
        User user = userService.getUserById(id);
        return RestResponse.success(user);
    }

    @RequestMapping("getList")
    public RestResponse<List<User>> getUserList(@RequestBody User user) {
        List<User> users = userService.getUserByQuery(user);
        return RestResponse.success(users);
    }

    //----------------------注册----------------------------------
    @RequestMapping("add")
    public RestResponse<User> add(@RequestBody User user) {
        userService.addAccount(user, user.getEnableUrl());
        return RestResponse.success();
    }

    /**
     * 主要激活key的验证
     */
    @RequestMapping("enable")
    public RestResponse<Object> enable(String key) {
        userService.enable(key);
        return RestResponse.success();
    }

    //------------------------登录/鉴权--------------------------

    @RequestMapping("auth")
    public RestResponse<User> auth(@RequestBody User user) {
        User finalUser = userService.auth(user.getEmail(), user.getPasswd());
        return RestResponse.success(finalUser);
    }

    @RequestMapping("get")
    public RestResponse<User> getUser(String token) {
//        //模拟延时
//        if(token!=null){
//            try{
//                Thread.sleep(30000);
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
        User finalUser = userService.getLoginedUserByToken(token);
        return RestResponse.success(finalUser);
    }

    @RequestMapping("logout")
    public RestResponse<Object> logout(String token) {
        userService.invalidate(token);
        return RestResponse.success();
    }

    @RequestMapping("update")
    public RestResponse<User> update(@RequestBody User user) {
        User updateUser = userService.updateUser(user);
        return RestResponse.success(updateUser);
    }

    @RequestMapping("reset")
    public RestResponse<User> reset(String key, String password) {
        User updateUser = userService.reset(key, password);
        return RestResponse.success(updateUser);
    }

    @RequestMapping("getKeyEmail")
    public RestResponse<String> getKeyEmail(String key) {
        String email = userService.getResetKeyEmail(key);
        return RestResponse.success(email);
    }

    @RequestMapping("resetNotify")
    public RestResponse<User> resetNotify(String email, String url) {
        userService.resetNotify(email, url);
        return RestResponse.success();
    }
}
