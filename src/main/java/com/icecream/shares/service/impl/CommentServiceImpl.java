package com.icecream.shares.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icecream.shares.mapper.CommentMapper;
import com.icecream.shares.pojo.Comment;
import com.icecream.shares.service.CommentService;
import com.icecream.shares.vo.CommentVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dqbryant
 * @create 2020/11/18 15:11
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public IPage<CommentVo> getCommentsPage(Integer postId, Integer pageNum, Integer pageSize) {
        Page<CommentVo> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page, postId);
    }
}
