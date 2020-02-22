package com.mooc.boss.house.api.hystrix;

import com.mooc.boss.house.api.common.RestResponse;
import com.mooc.boss.house.api.feign.CommentFeignService;
import com.mooc.boss.house.api.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentFeignServiceHystrix implements CommentFeignService {
    @Override
    public void addComment(CommentReq commentReq) {
        throw new RuntimeException("服务异常，请稍后重试！");
    }

    @Override
    public RestResponse<List<Comment>> listComment(CommentReq commentReq) {
        return new RestResponse<>();
    }

    @Override
    public RestResponse<ListResponse<Blog>> getBlogs(BlogQueryReq blogQueryReq) {
        return new RestResponse<>();
    }

    @Override
    public RestResponse<Blog> getBlog(int id) {
        throw new RuntimeException("服务异常，请稍后重试！");
    }
}
