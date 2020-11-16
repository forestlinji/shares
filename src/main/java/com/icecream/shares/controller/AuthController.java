package com.icecream.shares.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/user")
@RestController
public class AuthController {
    //单个参数post传参演示
    @PostMapping("test")
    public String test(@RequestBody Map map){
        String username = (String) map.get("username");
        return username;
    }


}
