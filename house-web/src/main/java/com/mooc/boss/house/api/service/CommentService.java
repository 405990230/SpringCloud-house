package com.mooc.boss.house.api.service;

import com.mooc.boss.house.api.common.PageData;
import com.mooc.boss.house.api.common.PageParams;
import com.mooc.boss.house.api.feign.CommentFeignService;
import com.mooc.boss.house.api.model.*;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentFeignService commentFeignService;

    public void addHouseComment(Long houseId, String content, Long userId) {
        CommentReq commentReq = new CommentReq();
        commentReq.setHouseId(houseId);
        commentReq.setContent(content);
        commentReq.setUserId(userId);
        commentReq.setType(1);
        commentFeignService.addComment(commentReq);
    }

    public void addBlogComment(Integer blogId, String content, Long userId) {
        CommentReq commentReq = new CommentReq();
        commentReq.setBlogId(blogId);
        commentReq.setContent(content);
        commentReq.setUserId(userId);
        commentReq.setType(2);
        commentFeignService.addComment(commentReq);
    }


    public List<Comment> getHouseComments(long id) {
        CommentReq commentReq = new CommentReq();
        commentReq.setHouseId(id);
        commentReq.setType(1);
        commentReq.setSize(8);
        return commentFeignService.listComment(commentReq).getResult();
    }

    public List<Comment> getBlogComments(int id) {
        CommentReq commentReq = new CommentReq();
        commentReq.setBlogId(id);
        commentReq.setType(2);
        commentReq.setSize(8);
        return commentFeignService.listComment(commentReq).getResult();
    }


    public Blog queryOneBlog(int id) {
        return commentFeignService.getBlog(id).getResult();
    }


    public PageData<Blog> queryBlogs(Blog query, PageParams build) {
        BlogQueryReq blogQueryReq = new BlogQueryReq();
        blogQueryReq.setBlog(query);
        blogQueryReq.setLimit(build.getLimit());
        blogQueryReq.setOffset(build.getOffset());
        ListResponse<Blog> listResponse = commentFeignService.getBlogs(blogQueryReq).getResult();
        Pair<List<Blog>, Long> pair = ImmutablePair.of(listResponse.getList(),listResponse.getCount());
        return PageData.buildPage(pair.getKey(), pair.getValue(), build.getPageSize(), build.getPageNum());
    }


}
