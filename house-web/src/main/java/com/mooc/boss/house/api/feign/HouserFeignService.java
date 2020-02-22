package com.mooc.boss.house.api.feign;

import com.mooc.boss.house.api.common.RestResponse;
import com.mooc.boss.house.api.hystrix.HouserFeignFallBackFactory;
import com.mooc.boss.house.api.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "HOUSE-SERVICE",fallbackFactory = HouserFeignFallBackFactory.class)
public interface HouserFeignService {
    @RequestMapping("/house/allCitys")
    RestResponse<List<City>> getAllCitys();

    @RequestMapping("/house/allCommunitys")
    RestResponse<List<Community>> getAllCommunitys();

    @RequestMapping("/house/add")
    void addHouse(@RequestBody House house);

    @RequestMapping("/house/rating")
    void rating(@RequestParam("id") Long id, @RequestParam("rating") Double rating);

    @RequestMapping("/house/addUserMsg")
    void addUserMsg(@RequestBody UserMsg userMsg);

    @RequestMapping("/house/lastest")
    RestResponse<List<House>>  getLastest();

    @RequestMapping(value = "/house/list",method= RequestMethod.POST, consumes="application/json; charset=UTF-8")
    RestResponse<ListResponse<House>>  getHouses(@RequestBody HouseQueryReq req);

    @RequestMapping(value = "/house/hot",method= RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    RestResponse<List<House>>  getHotHouse(@RequestParam("size") Integer recomSize);

    @RequestMapping("/house/detail")
    RestResponse<House> getOneHouse(@RequestParam("id") long id);

    @RequestMapping("/house/bind")
    void bindOrInBind(@RequestBody HouseUserReq req);

}
