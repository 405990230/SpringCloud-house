package com.mooc.boss.house.api.model;

import lombok.Data;

import java.util.Date;

@Data
public class Recommend {

    private Long id;
    private Long houseId;
    private Integer type;
    private Integer userId;
    private Integer district;
    private Date createTime;

}
