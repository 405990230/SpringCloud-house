package com.mooc.boss.house.api.model;

import lombok.Data;

@Data
public class BlogQueryReq {

    private Blog blog;

    private Integer limit;

    private Integer offset;


}
