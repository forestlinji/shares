package com.icecream.shares;

import cn.hutool.core.lang.Assert;
import com.icecream.shares.pojo.User;
import com.icecream.shares.service.UserService;
import com.icecream.shares.vo.LoginUsernameVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class TestAuthController {
    @Autowired
    public UserService userService;
    public final String baseUrl = "http://localhost:11451";
    public String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJST0xFIjoidXNlciIsImlzcyI6IkZvcmVzdGpDbGltYiIsImlhdCI6MTYwNjExODQyOSwic3ViIjoiMyIsImV4cCI6MTYwODcxMDQyOX0.-dadapsfD1hWZ8EtjxKtA0p3yEx90vf7cXC_aL2RlW4";

    @Test
    public void testSelectUser(){
        User user = userService.findUserByPhone("18959121464");
        System.out.println(user);
    }



//    @Test
//    public void testauthInfo(){
//        RestTemplate restTemplate = new RestTemplate();
//        //需要登录，在header中携带token
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.add("Authorization", token);
//        //post请求参数放在body里面，get请求参数直接拼接url
//        HttpEntity httpEntity = new HttpEntity<Object>(null,requestHeaders);
//        ResponseEntity<Object> response = restTemplate.exchange(baseUrl + "/user/authInfo", HttpMethod.GET, httpEntity, Object.class);
//        Assert.isTrue(response.getStatusCodeValue() == 200);
//    }

}
