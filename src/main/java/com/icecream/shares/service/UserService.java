package com.icecream.shares.service;


import com.icecream.shares.pojo.UserInfo;
import com.icecream.shares.pojo.User;

import org.springframework.stereotype.Service;


public interface UserService {

    /**
     * 根据用户id获取用户信息
     * @param userId 用户id
     * @return 用户数据
     */
    UserInfo getUserInfo(Integer userId);

    //根据手机号查询用户
    User findUserByPhone(String phone);
    // 用户注册
    User register(User user);

}
