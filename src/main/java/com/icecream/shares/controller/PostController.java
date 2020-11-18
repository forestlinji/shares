package com.icecream.shares.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icecream.shares.annotation.Auth;
import com.icecream.shares.interceptor.LoginInterceptor;
import com.icecream.shares.pojo.*;
import com.icecream.shares.service.CommentService;
import com.icecream.shares.service.PostService;
import com.icecream.shares.vo.CommentVo;
import com.icecream.shares.vo.SearchPostVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {
    @Autowired
    public PostService postService;
    @Autowired
    CommentService commentServiceImpl;

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
    @Auth
    @DeleteMapping("/delete")
    public ResponseJson<Object> deletePost(Integer postId){
        Integer userId = Integer.parseInt(LoginInterceptor.getUserId());
        Post post = postService.getById(postId);
        if(post == null || !post.getReleaseId().equals(userId)){
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }
        if(postService.removeById(postId)){
            return new ResponseJson<>(ResultCode.SUCCESS);
        }else {
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }
    }
    @Auth
    @PostMapping("/comment")
    public ResponseJson<Object> comment(@RequestBody Map<String, String> map){
        Integer userId = Integer.parseInt(LoginInterceptor.getUserId());
        Integer postId = Integer.parseInt(map.get("postId"));
        Post post = postService.getById(postId);
        if(post == null){
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }
        String content = map.get("content");
        Timestamp releaseTime = new Timestamp(System.currentTimeMillis());
        Comment comment = new Comment(postId, userId, content, releaseTime);
        if(commentServiceImpl.save(comment)){
            return new ResponseJson<>(ResultCode.SUCCESS);
        }else {
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }
    }
    @Auth
    @DeleteMapping("/deleteComment")
    public ResponseJson<Object> deleteComment(Integer commentId){
        Comment comment = commentServiceImpl.getById(commentId);
        Integer userId = Integer.parseInt(LoginInterceptor.getUserId());
        if(comment == null || !comment.getCommentUserId().equals(userId)){
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }
        if(commentServiceImpl.removeById(commentId)){
            return new ResponseJson<>(ResultCode.SUCCESS);
        }else {
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }
    }
    @GetMapping("/getComments")
    public ResponseJson<PageResult<CommentVo>> getComments(Integer postId,
                                                           @RequestParam(defaultValue = "1",required = false) Integer pageNum,
                                                           @RequestParam(defaultValue = "1",required = false) Integer pageSize){
        Post post = postService.getById(postId);
        if(post == null){
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }
        return new ResponseJson<>(ResultCode.SUCCESS, new PageResult<>(commentServiceImpl.getCommentsPage(postId, pageNum, pageSize)));
    }
}
