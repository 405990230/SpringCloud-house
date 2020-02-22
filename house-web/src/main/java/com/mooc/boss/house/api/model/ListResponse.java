package com.mooc.boss.house.api.model;

import lombok.Data;

import java.util.List;

@Data
public class ListResponse<T> {

    private List<T> list;

    private Long count;

    public static <T> ListResponse<T> build(List<T> list, Long count) {
        ListResponse<T> response = new ListResponse<T>();
        response.setCount(count);
        response.setList(list);
        return response;
    }

    @Override
    public String toString() {
        return "ListResponse [list=" + list + ", count=" + count + "]";
    }


}
