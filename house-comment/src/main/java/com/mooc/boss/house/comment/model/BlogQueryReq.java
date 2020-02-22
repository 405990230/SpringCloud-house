package com.mooc.boss.house.comment.model;

import lombok.Data;

@Data
public class BlogQueryReq {

    private Blog blog;

    private Integer limit;

    private Integer offset;

}
