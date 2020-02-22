package com.mooc.boss.house.comment.mapper;

import com.mooc.boss.house.comment.model.Blog;
import com.mooc.boss.house.comment.model.LimitOffset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper {

    public List<Blog> selectBlog(@Param("blog") Blog blog, @Param("pageParams") LimitOffset limitOffset);

    public Long selectBlogCount(Blog query);

}
