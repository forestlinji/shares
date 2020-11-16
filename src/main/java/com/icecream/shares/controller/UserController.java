package com.icecream.shares.controller;

import com.icecream.shares.annotation.Auth;
import com.icecream.shares.interceptor.LoginInterceptor;
import com.icecream.shares.pojo.ResponseJson;
import com.icecream.shares.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Auth
    @GetMapping("/getUserInfo")
    public ResponseJson<UserInfo> getUserInfo(){
        int userId = Integer.parseInt(LoginInterceptor.getUserId());

    }
}
