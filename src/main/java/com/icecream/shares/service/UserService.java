package com.icecream.shares.service;

import com.icecream.shares.pojo.UserInfo;
import org.springframework.stereotype.Service;


public interface UserService {
    /**
     * 根据用户id获取用户信息
     * @param userId 用户id
     * @return 用户数据
     */
    UserInfo getUserInfo(Integer userId);
}
