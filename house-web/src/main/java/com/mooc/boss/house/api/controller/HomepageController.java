package com.mooc.boss.house.api.controller;

import com.mooc.boss.house.api.model.House;
import com.mooc.boss.house.api.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomepageController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("index")
    public String accountsRegister(ModelMap modelMap) {
        List<House> houses = houseService.getLastest();
        modelMap.put("recomHouses", houses);
        return "/homepage/index";
    }


    @RequestMapping("")
    public String index(ModelMap modelMap) {
        return "redirect:/index";
    }
}
