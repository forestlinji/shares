package com.icecream.shares.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icecream.shares.annotation.Auth;
import com.icecream.shares.interceptor.LoginInterceptor;
import com.icecream.shares.pojo.*;
import com.icecream.shares.service.ConcernService;
import com.icecream.shares.service.UserService;
import com.icecream.shares.vo.UserInfoVo;
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
    com.icecream.shares.service.UserInfoService
    @Autowired
    ConcernService concernServiceImpl;
    @Auth
    @GetMapping("/getUserInfo")
    public ResponseJson<UserInfo> getUserInfo(){
        Integer userId = Integer.parseInt(LoginInterceptor.getUserId());

        UserInfo userInfo = userInfoServiceImpl.get(userId);
        if (userInfo == null){
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }else {
            return new ResponseJson<>(ResultCode.SUCCESS, userInfo);
        }
    }
    @GetMapping("/username")
    public ResponseJson<Object> checkUsername(String username){
        UserInfo userInfo = userInfoServiceImpl.getUserByUsername(username);
        if(null == userInfo){
            return new ResponseJson<>(ResultCode.SUCCESS);
        }else {
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }
    }
    @Auth
    @PostMapping("/updateInfo")
    public ResponseJson<Object> updateInfo(@RequestBody UserInfoVo userInfoVo){
        Integer userId = Integer.parseInt(LoginInterceptor.getUserId());
        UserInfo userInfo = userInfoServiceImpl.getById(userId);
        if(userInfo == null){
            userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.update(userInfoVo);
            if(userInfoServiceImpl.save(userInfo)){
                return new ResponseJson<>(ResultCode.SUCCESS);
            }
        }else {
            userInfo.update(userInfoVo);
            userInfoServiceImpl.updateById(userInfo);
        }
    }
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

//        return new ResponseJson<>(ResultCode.UNVALIDPARAMS);

    }
}
