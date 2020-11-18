package com.icecream.shares.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.icecream.shares.pojo.PageResult;
import com.icecream.shares.pojo.Post;
import com.icecream.shares.vo.SearchPostVo;

import java.io.File;

public interface PostService extends IService<Post> {
    /**
     * 根据postId查找已过审的帖子
     * @param postId
     * @return
     */
    Post findCheckedPostById(Integer postId);

    /**
     * 根据关键词搜索帖子或展示帖子列表
     *
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    public PageResult<SearchPostVo> getPostBySearch(Integer pageNum, Integer pageSize, String keyword);

    public void saveImagesLocal(File[] images, Integer postId);
}
