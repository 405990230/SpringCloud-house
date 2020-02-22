package com.mooc.boss.house.service.service;

import com.mooc.boss.house.service.mapper.CityMapper;
import com.mooc.boss.house.service.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    public List<City> getAllCitys() {
        City query = new City();
        return cityMapper.selectCitys(query);
    }

}
