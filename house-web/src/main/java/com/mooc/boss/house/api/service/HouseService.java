package com.mooc.boss.house.api.service;

import com.google.common.base.Joiner;
import com.mooc.boss.house.api.common.HouseUserType;
import com.mooc.boss.house.api.common.PageData;
import com.mooc.boss.house.api.common.PageParams;
import com.mooc.boss.house.api.feign.HouserFeignService;
import com.mooc.boss.house.api.model.*;
import com.mooc.boss.house.api.utils.BeanHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class HouseService {


    @Autowired
    private FileService fileService;

    @Autowired
    private HouserFeignService houserFeignService;


    public void updateRating(Long id, Double rating) {
        houserFeignService.rating(id, rating);
    }

    public void addHouse(House house, User user) {
        if (house.getHouseFiles() != null && !house.getHouseFiles().isEmpty()) {
            List<MultipartFile> files = house.getHouseFiles();
            String imags = Joiner.on(",").join(fileService.getImgPaths(files));
            house.setImages(imags);
        }
        if (house.getFloorPlanFiles() != null && !house.getFloorPlanFiles().isEmpty()) {
            List<MultipartFile> files = house.getFloorPlanFiles();
            String floorPlans = Joiner.on(",").join(fileService.getImgPaths(files));
            house.setFloorPlan(floorPlans);
        }
        BeanHelper.setDefaultProp(house, House.class);
        BeanHelper.onInsert(house);
        house.setUserId(user.getId());
        houserFeignService.addHouse(house);
    }


    public List<Community> getAllCommunitys() {
        return houserFeignService.getAllCommunitys().getResult();
    }


    public List<City> getAllCitys() {
        return houserFeignService.getAllCitys().getResult();
    }


    public void addUserMsg(UserMsg userMsg) {
        houserFeignService.addUserMsg(userMsg);
    }


    public List<House> getLastest() {
        return houserFeignService.getLastest().getResult();
    }

    public PageData<House> queryHouse(House query, PageParams build) {
        HouseQueryReq req = new HouseQueryReq();
        req.setLimit(build.getLimit());
        req.setOffset(build.getOffset());
        req.setQuery(query);
        ListResponse<House> result = houserFeignService.getHouses(req).getResult();
        return PageData.<House>buildPage(result.getList(), result.getCount(), build.getPageSize(), build.getPageNum());
    }

    public List<House> getHotHouse(Integer recomSize) {
        List<House> list = houserFeignService.getHotHouse(recomSize).getResult();
        return list;
    }

    public House queryOneHouse(long id) {
        return houserFeignService.getOneHouse(id).getResult();
    }

    public void bindUser2House(Long houseId, Long userId, boolean bookmark) {
        HouseUserReq req = new HouseUserReq();
        req.setUnBind(false);
        req.setBindType(HouseUserType.BOOKMARK.value);
        req.setUserId(userId);
        req.setHouseId(houseId);
        houserFeignService.bindOrInBind(req);
    }

    public void unbindUser2House(Long houseId, Long userId, boolean book) {
        HouseUserReq req = new HouseUserReq();
        req.setUnBind(true);
        if (book) {
            req.setBindType(HouseUserType.BOOKMARK.value);
        } else {
            req.setBindType(HouseUserType.SALE.value);
        }
        req.setUserId(userId);
        req.setHouseId(houseId);
        houserFeignService.bindOrInBind(req);
    }


}
