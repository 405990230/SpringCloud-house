package com.mooc.boss.house.api.hystrix;

import com.mooc.boss.house.api.feign.HouserFeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HouserFeignFallBackFactory implements FallbackFactory<HouserFeignService> {
    @Override
    public HouserFeignService create(Throwable throwable) {
        return new HouserFeignServiceHystrix();
    }
}
