package com.icecream.shares.controller;

import Jama.Matrix;
import com.alibaba.fastjson.JSONObject;
import com.icecream.shares.pojo.Post;
import com.icecream.shares.pojo.ResponseJson;
import com.icecream.shares.pojo.ResultCode;
import com.icecream.shares.service.PostService;
import com.icecream.shares.utils.DecideUtil;
import com.icecream.shares.vo.DecideVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author dqbryant
 * @create 2020/11/19 10:32
 */
@RestController
@CrossOrigin
public class DecideController {
    @Autowired
    PostService postService;
    @PostMapping("/decide")
    public ResponseJson<Map<String, String>> getDecision(@RequestBody DecideVo decideVo){
        List<Post> posts = postService.listByIds(decideVo.getPostIds());
        List<Double> vector = DecideUtil.decide(posts, decideVo);
        int index = 0;
        for(int i = 1;i < vector.size();i++){
            if(vector.get(i) > vector.get(index)){
                index = i;
            }
        }
        Map<String, String> map = new HashMap<>(10);
        Post post = posts.get(index);
        map.put("postId", String.valueOf(post.getPostId()));
        map.put("title", post.getTitle());
        return new ResponseJson<>(ResultCode.SUCCESS, map);
    }
}
