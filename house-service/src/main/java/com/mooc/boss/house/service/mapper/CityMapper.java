package com.mooc.boss.house.service.mapper;

import com.mooc.boss.house.service.model.City;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CityMapper {

    public List<City> selectCitys(City city);

}
