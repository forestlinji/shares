package com.icecream.shares.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icecream.shares.mapper.NoticeMapper;
import com.icecream.shares.mapper.PostMapper;
import com.icecream.shares.pojo.Notice;
import com.icecream.shares.pojo.PageResult;
import com.icecream.shares.pojo.Post;
import com.icecream.shares.service.PostService;
import com.icecream.shares.vo.PostVo;
import com.icecream.shares.vo.PostVo2;
import com.icecream.shares.vo.SearchPostVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Autowired
    public PostMapper postMapper;

    @Override
    public Post findCheckedPostById(Integer postId) {
        Post post = postMapper.selectOne(new QueryWrapper<Post>().eq("post_id", postId).eq("check_state", 1));
        return post;
    }
    @Override
    public PageResult<SearchPostVo> getPostBySearch(Integer pageNum, Integer pageSize, String keyword) {
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        if(!StringUtils.isBlank(keyword)){
            wrapper.like("title", keyword);
        }
        wrapper.eq("check_state", 1).orderByDesc("release_time");
        IPage<Post> page = this.page(new Page<Post>(pageNum, pageSize), wrapper);
        List<SearchPostVo> postLists = page.getRecords().stream().map((item) -> {
            SearchPostVo searchPostVo = new SearchPostVo();
            BeanUtils.copyProperties(item, searchPostVo);
            return searchPostVo;
        }).collect(Collectors.toList());
        PageResult<SearchPostVo> searchPostVoPageResult = new PageResult<>();
        searchPostVoPageResult.setRecords(postLists);
        searchPostVoPageResult.setCurrent(page.getCurrent());
        searchPostVoPageResult.setSize(page.getSize());
        searchPostVoPageResult.setTotal(page.getTotal());
        return searchPostVoPageResult;
    }

    @Override
    public void saveImagesLocal(File[] images,Integer postId) {
        File file=new File("./shares/post_cover_"+ postId);
        if(!file.exists()){//如果文件夹不存在
            file.mkdir();//创建文件夹
        }

    }


    @Override
    public List<PostVo> getPostProcess(Integer userId, Integer type) {
        return baseMapper.getPostProcess(userId, type);
    }

    @Override
    public List<PostVo> getCollections(Integer userId, Integer type) {
        return baseMapper.getCollections(userId, type);
    }

    @Override
    public IPage<PostVo2> getHistory(Integer userId, Integer pageNum, Integer pageSize) {
        Page<PostVo2> postVo2Page = new Page<>(pageNum, pageSize);
        return baseMapper.getPostHistory(userId, postVo2Page);
    }
}
