package com.icecream.shares.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icecream.shares.mapper.UserInfoMapper;
import com.icecream.shares.pojo.UserInfo;
import com.icecream.shares.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public UserInfo getUserInfo(Integer userId) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return userInfoMapper.selectOne(queryWrapper);
    }
}
