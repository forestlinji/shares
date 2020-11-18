package com.icecream.shares.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icecream.shares.pojo.Comment;
import com.icecream.shares.vo.CommentVo;

import java.util.List;

/**
 * @author dqbryant
 * @create 2020/11/18 15:10
 */
public interface CommentService extends IService<Comment> {
    IPage<CommentVo> getCommentsPage(Integer postId, Integer pageNum, Integer pageSize);
}
