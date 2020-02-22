package com.mooc.boss.house.api.feign;

import com.mooc.boss.house.api.common.RestResponse;
import com.mooc.boss.house.api.hystrix.CommentFeignFallBackFactory;
import com.mooc.boss.house.api.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(value = "HOUSE-COMMENT",fallbackFactory = CommentFeignFallBackFactory.class)
public interface CommentFeignService {

    @RequestMapping("/comment/add")
    void addComment(@RequestBody CommentReq commentReq);

    @RequestMapping("/comment/list")
    RestResponse<List<Comment>> listComment(@RequestBody CommentReq commentReq);

    @RequestMapping(value = "/blog/list",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    RestResponse<ListResponse<Blog>> getBlogs(@RequestBody BlogQueryReq blogQueryReq);

    @RequestMapping(value="/blog/one",method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    RestResponse<Blog> getBlog(@RequestParam("id") int id);

}
