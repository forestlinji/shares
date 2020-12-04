package com.icecream.shares;

import com.icecream.shares.pojo.Post;
import com.icecream.shares.pojo.ResponseJson;
import com.icecream.shares.pojo.ResultCode;
import com.icecream.shares.service.NoticeService;
import com.icecream.shares.service.PostService;
import com.icecream.shares.utils.DecideUtil;
import com.icecream.shares.vo.DecideVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class TestDecideControl {
    @Autowired
    PostService postService;
    @Test
    public void testgetDecision(){
        DecideVo decideVo= new DecideVo();
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
        return ;
    }

    }


