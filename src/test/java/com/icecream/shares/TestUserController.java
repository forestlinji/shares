package com.icecream.shares;

import cn.hutool.core.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class TestUserController {
    public final String baseUrl = "http://localhost:11451";
    public String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJST0xFIjoidXNlciIsImlzcyI6IkZvcmVzdGpDbGltYiIsImlhdCI6MTYwNjExODQyOSwic3ViIjoiMyIsImV4cCI6MTYwODcxMDQyOX0.-dadapsfD1hWZ8EtjxKtA0p3yEx90vf7cXC_aL2RlW4";

    @Test
    public void testgetHeadLink(){
        RestTemplate restTemplate = new RestTemplate();
        //需要登录，在header中携带token
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", token);
        //post请求参数放在body里面，get请求参数直接拼接url
        HttpEntity httpEntity = new HttpEntity<Object>(null,requestHeaders);
        //get请求传参
        ResponseEntity<Object> response = restTemplate.exchange(baseUrl + "/user/getHeadLink?userId=1", HttpMethod.GET, httpEntity, Object.class);
        Assert.isTrue(response.getStatusCodeValue() == 200);
    }
}
