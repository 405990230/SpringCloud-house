package com.mooc.boss.house.service.model;

import lombok.Data;

@Data
public class HouseUserReq {

    private Long houseId;

    private Long userId;

    private Integer bindType;

    private boolean unBind;

    @Override
    public String toString() {
        return "HouseUserReq [houseId=" + houseId + ", userId=" + userId + "]";
    }


}
