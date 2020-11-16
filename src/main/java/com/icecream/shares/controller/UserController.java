package com.icecream.shares.controller;

import com.icecream.shares.annotation.Auth;
import com.icecream.shares.interceptor.LoginInterceptor;
import com.icecream.shares.pojo.Concern;
import com.icecream.shares.pojo.ResponseJson;
import com.icecream.shares.pojo.ResultCode;
import com.icecream.shares.pojo.UserInfo;
import com.icecream.shares.service.ConcernService;
import com.icecream.shares.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userServiceImpl;
    @Autowired
    ConcernService concernServiceImpl;
    @Auth
    @GetMapping("/getUserInfo")
    public ResponseJson<UserInfo> getUserInfo(){
        int userId = Integer.parseInt(LoginInterceptor.getUserId());

        UserInfo userInfo = userServiceImpl.getUserInfo(userId);
        if (userInfo == null){
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }else {
            return new ResponseJson<>(ResultCode.SUCCESS, userInfo);
        }
    }
//    @Auth
//    @PostMapping("/updateInfo")
//    public ResponseJson<Object> updateInfo(@RequestBody )
    @Auth
    @PostMapping("/addConcern")
    public  ResponseJson<Object> addConcern(@RequestBody Map<String, String> map){
        int userId = Integer.parseInt(LoginInterceptor.getUserId());
        int concernedUserId = Integer.parseInt(map.get("userId"));
        Concern concern = new Concern(userId, concernedUserId);
        if(concernServiceImpl.save(concern)){
            return new ResponseJson<>(ResultCode.SUCCESS);
        }else {
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }

        return new ResponseJson<>(ResultCode.UNVALIDPARAMS);

    }
}
