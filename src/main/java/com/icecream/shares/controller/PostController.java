package com.icecream.shares.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icecream.shares.pojo.PageResult;
import com.icecream.shares.pojo.Post;
import com.icecream.shares.pojo.ResponseJson;
import com.icecream.shares.pojo.ResultCode;
import com.icecream.shares.service.PostService;
import com.icecream.shares.vo.SearchPostVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {
    @Autowired
    public PostService postService;

    @GetMapping("get")
    public ResponseJson<Post> getPost(Integer postId){
        Post post = postService.findCheckedPostById(postId);
        if (post == null) {
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }
        return new ResponseJson<>(ResultCode.SUCCESS,post);
    }

    @GetMapping("search")
    public ResponseJson<PageResult<SearchPostVo>> search(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String keyword){
        if(pageNum <= 0 || pageSize <=0 || pageSize >=100){
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }
        PageResult<SearchPostVo> searchPostVoPageResult = postService.getPostBySearch(pageNum, pageSize, keyword);
        return new ResponseJson<>(ResultCode.SUCCESS, searchPostVoPageResult);
    }


}
