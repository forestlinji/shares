package com.icecream.shares;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.icecream.shares.interceptor.LoginInterceptor;
import com.icecream.shares.pojo.*;
import com.icecream.shares.service.CommentService;
import com.icecream.shares.service.PostOperationService;
import com.icecream.shares.service.PostService;
import com.icecream.shares.service.UserInfoService;
import com.icecream.shares.vo.CommentVo;
import com.icecream.shares.vo.PostDetailVo;
import com.icecream.shares.vo.PostStatusVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class TestPostController {
    public final String baseUrl = "http://localhost:11451";
    public String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJST0xFIjoidXNlciIsImlzcyI6IkZvcmVzdGpDbGltYiIsImlhdCI6MTYwNjExODQyOSwic3ViIjoiMyIsImV4cCI6MTYwODcxMDQyOX0.-dadapsfD1hWZ8EtjxKtA0p3yEx90vf7cXC_aL2RlW4";
    @Autowired
    public PostService postService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    PostOperationService postOperationService;
    @Autowired
    CommentService commentService;
//    @Test
//    public void testPostSearch(){
//        RestTemplate restTemplate = new RestTemplate();
//        //需要登录，在header中携带token
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.add("Authorization", token);
//        //post请求参数放在body里面，get请求参数直接拼接url
//        HttpEntity httpEntity = new HttpEntity<Object>(null,requestHeaders);
//        ResponseEntity<Object> response = restTemplate.exchange(baseUrl + "/post/search", HttpMethod.GET, httpEntity, Object.class);
//        Assert.isTrue(response.getStatusCodeValue() == 200);
//    }
    @Test
    public  void testgetPost() {
        Post post = postService.findCheckedPostById(1);
        if (post == null) {
            System.out.println("false");
            return;
        }
        PostDetailVo postDetailVo = new PostDetailVo();
        BeanUtils.copyProperties(post,postDetailVo);
        UserInfo userInfo = userInfoService.getById(post.getReleaseId());
        postDetailVo.setHeadLink(userInfo.getHeadLink());
        postDetailVo.setUsername(userInfo.getUsername());
        System.out.println(postDetailVo);
    }

    @Test
    public void testgetComments(){
        Post post = postService.getById(1);
        if(post == null){
            System.out.println("false");
            return;
        }
        System.out.println(post);

    }
    @Test
    public  void testgetStatus(){
        Post post = postService.findCheckedPostById(1);
        Integer userId = 1;
        if (post == null) {
            System.out.println("false");
        }
        List<PostOperation> postOperations = postOperationService.list(new QueryWrapper<PostOperation>()
                .eq("operator_id", userId)
                .eq("post_id", 1));
        PostStatusVo postStatusVo = new PostStatusVo();
        for (PostOperation postOperation : postOperations) {
            Integer type = postOperation.getOperationType();
            if(type == 1){
                postStatusVo.setCollect(true);
            }else if(type == 2){
                postStatusVo.setBad(true);
            }else{
                postStatusVo.setGood(true);
            }
        }
        System.out.println(postStatusVo);

    }
    @Test
    public  void testgetProcess(){
        Integer testnum = 1;
        if(testnum > 2 || testnum < 0){
            System.out.println("false");
        }
        testnum =5;
        Integer userId = 1;
        System.out.println(postService.getPostProcess(userId, testnum));
    }
    @Test
    public void testgetCollections(){
        Integer userId=1;
        System.out.println(postService.getCollections(userId, 1));
    }
    @Test
    public void testgetPostHistory(){
        System.out.println(postService.getHistory(1, 1, 4));
    }

    @Test
    public void testGetCommentsPage(){
        IPage<CommentVo> commentsPage = commentService.getCommentsPage(1, 1, 10);
        Assert.notNull(commentsPage);
    }
}
