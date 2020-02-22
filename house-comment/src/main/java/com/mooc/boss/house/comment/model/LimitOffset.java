package com.mooc.boss.house.comment.model;

import lombok.Data;

@Data
public class LimitOffset {

    private Integer limit;

    private Integer offset;

    public static LimitOffset build(Integer limit, Integer offset) {
        LimitOffset limitOffset = new LimitOffset();
        limitOffset.setLimit(limit);
        limitOffset.setOffset(offset);
        return limitOffset;
    }

}
