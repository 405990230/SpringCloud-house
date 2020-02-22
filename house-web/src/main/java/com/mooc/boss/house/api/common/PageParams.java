package com.mooc.boss.house.api.common;

import lombok.Data;

@Data
public class PageParams {

    private static final Integer PAGE_SIZE = 5;

    private Integer pageSize;

    private Integer pageNum;

    private Integer offset;

    private Integer limit;

    public static PageParams build() {
        return build(PAGE_SIZE, 1);
    }

    public static PageParams build(Integer pageSize, Integer pageNum) {
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        if (pageNum == null) {
            pageNum = 1;
        }
        return new PageParams(pageSize, pageNum);
    }


    public PageParams() {
        this(PAGE_SIZE, 1);
    }

    public PageParams(Integer pageSize, Integer pageNum) {
        assert pageSize != null;
        assert pageNum != null;
        this.pageSize = pageSize;
        this.pageNum = pageNum;

        this.offset = pageSize * (pageNum - 1);
        this.limit = pageSize;
    }


    @Override
    public String toString() {
        return "PageParams [pageSize=" + pageSize + ", pageNum=" + pageNum + "]";
    }

}
