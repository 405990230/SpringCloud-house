package com.mooc.boss.house.service.service;

import com.google.common.collect.Ordering;
import com.mooc.boss.house.service.common.LimitOffset;
import com.mooc.boss.house.service.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendService {

    private static final String HOT_HOUSE_KEY = "_hot_house";

    @Autowired
    private HouseService houseService;

    @Autowired
    private RedisTemplate redisTemplate;


    public List<House> getHotHouse(Integer size) {
        if (size == null) {
            size = 3;
        }
//  Set<String> idSet =  redisTemplate.opsForZSet().range(HOT_HOUSE_KEY, 0, -1);
        //bug修复，根据热度从高到底排序
        Set<String> idSet = redisTemplate.opsForZSet().reverseRange(HOT_HOUSE_KEY, 0, size - 1);
        List<Long> ids = idSet.stream().map(b -> Long.parseLong(b)).collect(Collectors.toList());
        House query = new House();
        query.setIds(ids);
        final List<Long> order = ids;
        List<House> houses = houseService.queryAndSetImg(query, LimitOffset.build(size, 0));
        Ordering<House> houseSort = Ordering.natural().onResultOf(hs -> {
            return order.indexOf(hs.getId());
        });
        return houseSort.sortedCopy(houses);
    }

    public void increaseHot(long id) {
        redisTemplate.opsForZSet().incrementScore(HOT_HOUSE_KEY, "" + id, 1.0D);
        redisTemplate.opsForZSet().removeRange(HOT_HOUSE_KEY, 0, -11);
    }

    public List<House> getLastest() {
        House query = new House();
        query.setSort("create_time");
        return houseService.queryAndSetImg(query, LimitOffset.build(8, 0));
    }


}
