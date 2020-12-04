package com.icecream.shares;

import com.icecream.shares.interceptor.LoginInterceptor;
import com.icecream.shares.pojo.Notice;
import com.icecream.shares.pojo.ResponseJson;
import com.icecream.shares.pojo.ResultCode;
import com.icecream.shares.service.NoticeService;
import com.icecream.shares.utils.MD5Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class TestNoticesControl {
    @Autowired
    NoticeService noticeServiceImpl;
    @Test
    public void show(){
        Integer userId = Integer.parseInt("1");
        System.out.println(noticeServiceImpl.getNoticesById(userId));

    }

}
