package com.mooc.boss.house.api.model;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Blog {
    private Integer id;
    private String tags;
    private String content;
    private String title;
    private Date createTime;
    private String digest;

    private List<String> tagList = Lists.newArrayList();

}
