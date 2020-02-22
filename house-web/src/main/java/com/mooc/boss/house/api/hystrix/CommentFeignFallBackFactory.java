package com.mooc.boss.house.api.hystrix;

import com.mooc.boss.house.api.common.RestResponse;
import com.mooc.boss.house.api.feign.CommentFeignService;
import com.mooc.boss.house.api.model.*;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentFeignFallBackFactory implements FallbackFactory<CommentFeignService> {
    @Override
    public CommentFeignService create(Throwable throwable) {
        return new CommentFeignServiceHystrix();
//        return new CommentFeignService(){
//            @Override
//            public void addComment(CommentReq commentReq) {
//                throw new RuntimeException(throwable.getMessage());
//            }
//
//            @Override
//            public RestResponse<List<Comment>> listComment(CommentReq commentReq) {
//                return new RestResponse<>();
//            }
//
//            @Override
//            public RestResponse<ListResponse<Blog>> getBlogs(BlogQueryReq blogQueryReq) {
//                return new RestResponse<>();
//            }
//
//            @Override
//            public RestResponse<Blog> getBlog(int id) {
//                throw new RuntimeException("服务异常，请稍后重试！");
//            }
//        };
    }
}
