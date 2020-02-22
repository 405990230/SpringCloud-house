package com.mooc.boss.house.api.hystrix;

import com.mooc.boss.house.api.common.RestResponse;
import com.mooc.boss.house.api.feign.HouserFeignService;
import com.mooc.boss.house.api.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HouserFeignServiceHystrix implements HouserFeignService {
    @Override
    public RestResponse<List<City>> getAllCitys() {
        throw new RuntimeException("服务异常，请稍后重试！");
    }

    @Override
    public RestResponse<List<Community>> getAllCommunitys() {
        throw new RuntimeException("服务异常，请稍后重试！");
    }

    @Override
    public void addHouse(House house) {
        throw new RuntimeException("服务异常，请稍后重试！");

    }

    @Override
    public void rating(Long id, Double rating) {
        throw new RuntimeException("服务异常，请稍后重试！");
    }

    @Override
    public void addUserMsg(UserMsg userMsg) {
        throw new RuntimeException("服务异常，请稍后重试！");
    }

    @Override
    public RestResponse<List<House>> getLastest() {
        return new RestResponse();
    }

    @Override
    public RestResponse<ListResponse<House>> getHouses(HouseQueryReq req) {
        return null;
    }

    @Override
    public RestResponse<List<House>> getHotHouse(Integer recomSize) {
        return null;
    }

    @Override
    public RestResponse<House> getOneHouse(long id) {
        return new RestResponse();
    }

    @Override
    public void bindOrInBind(HouseUserReq req) {
        throw new RuntimeException("服务异常，请稍后重试！");
    }
}
