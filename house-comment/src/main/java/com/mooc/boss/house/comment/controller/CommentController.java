package com.mooc.boss.house.comment.controller;

import com.google.common.base.Objects;
import com.mooc.boss.house.comment.common.RestResponse;
import com.mooc.boss.house.comment.model.Comment;
import com.mooc.boss.house.comment.model.CommentReq;
import com.mooc.boss.house.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/add")
    public RestResponse<Object> leaveComment(@RequestBody CommentReq commentReq) {
        Integer type = commentReq.getType();
        if (Objects.equal(1, type)) {
            commentService.addHouseComment(commentReq.getHouseId(), commentReq.getContent(), commentReq.getUserId());
        } else {
            commentService.addBlogComment(commentReq.getBlogId(), commentReq.getContent(), commentReq.getUserId());
        }
        return RestResponse.success();
    }

    @RequestMapping("/list")
    public RestResponse<List<Comment>> list(@RequestBody CommentReq commentReq) {
        Integer type = commentReq.getType();
        List<Comment> list = null;
        if (Objects.equal(1, type)) {
            list = commentService.getHouseComments(commentReq.getHouseId(), commentReq.getSize());
        } else {
            list = commentService.getBlogComments(commentReq.getBlogId(), commentReq.getSize());
        }
        return RestResponse.success(list);
    }


}