package com.icecream.shares.controller;

import com.icecream.shares.pojo.*;
import com.icecream.shares.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/user")
@RestController
public class AuthController {
    @Autowired
    public UserService userService;
    @Autowired
    public StringRedisTemplate redisTemplate;


    //单个参数post传参演示
    @PostMapping("test")
    public String test(@RequestBody Map map){
        String username = (String) map.get("username");
        return username;
    }

    @PostMapping("/login/phone")
    public ResponseJson loginByPhone(@Valid @RequestBody LoginPhoneVo loginPhoneVo){
        String phone = loginPhoneVo.getPhone();
        String code = loginPhoneVo.getCode();
        String correct = redisTemplate.opsForValue().get(phone);
        if(!Objects.equals(code, correct)){
            return new ResponseJson(ResultCode.WRONGCODE);
        }
        User user = userService.findUserByPhone(phone);
        if(user == null){
            user = new User();
            user.setPhone(phone);
            user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
            user.setLastChangetime(user.getRegisterTime());
            userService.register(user);
        }
//        User user = userService.findUserByPhone("18959121464");
        return new ResponseJson(ResultCode.SUCCESS, user);
    }



}
