package com.mooc.boss.house.api.controller;

import com.mooc.boss.house.api.inteceptor.UserContext;
import com.mooc.boss.house.api.model.User;
import com.mooc.boss.house.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    @RequestMapping(value = "comment/leaveComment")
    public String leaveComment(String content, Long houseId, ModelMap modelMap) {
        User user = UserContext.getUser();
        Long userId = user.getId();
        commentService.addHouseComment(houseId, content, userId);
        return "redirect:/house/detail?id=" + houseId;
    }

    @RequestMapping(value = "comment/leaveBlogComment")
    public String leaveBlogComment(String content, Integer blogId, ModelMap modelMap) {
        User user = UserContext.getUser();
        Long userId = user.getId();
        commentService.addBlogComment(blogId, content, userId);
        return "redirect:/blog/detail?id=" + blogId;
    }

}
