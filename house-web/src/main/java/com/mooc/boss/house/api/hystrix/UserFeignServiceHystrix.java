package com.mooc.boss.house.api.hystrix;

import com.mooc.boss.house.api.common.RestResponse;
import com.mooc.boss.house.api.feign.UserFeignService;
import com.mooc.boss.house.api.model.Agency;
import com.mooc.boss.house.api.model.ListResponse;
import com.mooc.boss.house.api.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserFeignServiceHystrix implements UserFeignService {
    @Override
    public RestResponse<String> getUserName(Long id) {
        return null;
    }

    @Override
    public RestResponse<List<User>> getUserList(User query) {
        return null;
    }

    @Override
    public RestResponse<User> addUser(User account) {
        return null;
    }

    @Override
    public RestResponse<User> enable(String key) {
        return null;
    }

    @Override
    public RestResponse<User> authUser(User user) {
        return null;
    }

    @Override
    public void logout(String token) {

    }

    @Override
    public RestResponse<User> getUserByToken(String token) {
        return null;
    }

    @Override
    public RestResponse<List<Agency>> getAllAgency() {
        return null;
    }

    @Override
    public RestResponse<User> updateUser(User user) {
        return null;
    }

    @Override
    public RestResponse<User> getAgentById(Long id) {
        return null;
    }

    @Override
    public RestResponse<Agency> getAgencyById(Integer id) {
        return null;
    }

    @Override
    public void addAgency(Agency agency) {

    }

    @Override
    public RestResponse<ListResponse<User>> getAgentList(Integer limit, Integer offset) {
        return null;
    }

    @Override
    public RestResponse<String> getEmail(String key) {
        return null;
    }

    @Override
    public RestResponse<User> reset(String key, String password) {
        return null;
    }

    @Override
    public void resetNotify(String email, String url) {

    }
}
