package com.mooc.boss.house.api.service;

import com.mooc.boss.house.api.common.PageData;
import com.mooc.boss.house.api.common.PageParams;
import com.mooc.boss.house.api.feign.UserFeignService;
import com.mooc.boss.house.api.model.Agency;
import com.mooc.boss.house.api.model.ListResponse;
import com.mooc.boss.house.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyService {

    @Autowired
    private UserFeignService userFeignService;


    public List<Agency> getAllAgency() {
        return userFeignService.getAllAgency().getResult();
    }

    public Agency getAgency(Integer id) {
        return userFeignService.getAgencyById(id).getResult();
    }

    public void add(Agency agency) {
        userFeignService.addAgency(agency);
    }

    public PageData<User> getAllAgent(PageParams pageParams) {
        ListResponse<User> result = userFeignService.getAgentList(pageParams.getLimit(), pageParams.getOffset()).getResult();
        Long count = result.getCount();
        return PageData.<User>buildPage(result.getList(), count, pageParams.getPageSize(), pageParams.getPageNum());
    }


    public User getAgentDetail(Long id) {
        return userFeignService.getAgentById(id).getResult();
    }


}
