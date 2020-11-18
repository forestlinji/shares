package com.icecream.shares.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icecream.shares.annotation.Auth;
import com.icecream.shares.interceptor.LoginInterceptor;
import com.icecream.shares.pojo.*;
import com.icecream.shares.service.ConcernService;
import com.icecream.shares.service.PostService;
import com.icecream.shares.service.UserInfoService;
import com.icecream.shares.service.UserService;
import com.icecream.shares.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userServiceImpl;
    @Autowired
    UserInfoService userInfoServiceImpl;
    @Autowired
    ConcernService concernServiceImpl;
    @Auth
    @GetMapping("/getUserInfo")
    public ResponseJson<UserInfo> getUserInfo(){
        Integer userId = Integer.parseInt(LoginInterceptor.getUserId());

        UserInfo userInfo = userInfoServiceImpl.getById(userId);
        if (userInfo == null){
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }else {
            return new ResponseJson<>(ResultCode.SUCCESS, userInfo);
        }
    }
    @GetMapping("/username")
    public ResponseJson<Object> checkUsername(String username){
        UserInfo userInfo = userInfoServiceImpl.getUserInfoByUsername(username);
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
        System.out.println(userInfoVo);
        userInfoVo.setUserId(userId);
        if(userInfoServiceImpl.update(userInfoVo)){
            return new ResponseJson<>(ResultCode.SUCCESS);
        }else {
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }
    }
    @Auth
    @PostMapping("/addConcern")
    public  ResponseJson<Object> addConcern(@RequestBody Map<String, String> map){
        int userId = Integer.parseInt(LoginInterceptor.getUserId());
        int concernedUserId = Integer.parseInt(map.get("userId"));
        User user = userServiceImpl.getById(concernedUserId);
        if(null == user || userId == concernedUserId){
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }
        Concern concern = new Concern(userId, concernedUserId);
        if(concernServiceImpl.addConcern(concern) == 1){
            return new ResponseJson<>(ResultCode.SUCCESS);
        }else {
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS, "重复关注");
        }

//        return new ResponseJson<>(ResultCode.UNVALIDPARAMS);

    }
    @Auth
    @DeleteMapping("/cancelConcern")
    public  ResponseJson<Object> cancerConcern(@RequestBody Map<String, String> map) {
        //自己的id
        Integer userId = Integer.parseInt(LoginInterceptor.getUserId());
        //对方的id
        Integer cancelConcernedUserId = Integer.parseInt(map.get("userId"));
        User user = userServiceImpl.getById(cancelConcernedUserId);
        if(null == user || userId.equals(cancelConcernedUserId)){
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }
        Concern concern = new Concern(userId, cancelConcernedUserId);
        if (concernServiceImpl.cancelConcern(concern) == 1) {
            return new ResponseJson<>(ResultCode.SUCCESS);
        } else {
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);

        }
    }
    @Auth
    @GetMapping("/otherInfo")
    public ResponseJson<UserInfo> otherInfo(Integer userId){

        UserInfo userInfo = userInfoServiceImpl.getById(userId);
        if (userInfo == null){
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }else {
            return new ResponseJson<>(ResultCode.SUCCESS, userInfo);
        }
    }

    @GetMapping("/getHeadLink")
    public ResponseJson<Map<String,String>> getHeadLink(Integer userId){
        UserInfo userInfo = userInfoServiceImpl.getById(userId);
        if(null != userInfo){
            Map<String, String> map =new HashMap<>();
            map.put("headLink", userInfo.getHeadLink());
            return new ResponseJson<>(ResultCode.SUCCESS, map);
        }else {
            return new ResponseJson<>(ResultCode.UNVALIDPARAMS);
        }
    }
}
