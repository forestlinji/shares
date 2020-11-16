package com.icecream.shares.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.icecream.shares.mapper.UserInfoMapper;
import com.icecream.shares.pojo.UserInfo;

import com.icecream.shares.mapper.UserMapper;
import com.icecream.shares.pojo.Prefer;
import com.icecream.shares.pojo.User;
import com.icecream.shares.service.PreferService;

import com.icecream.shares.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;
    @Autowired
    public PreferService preferService;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public UserInfo getUserInfo(Integer userId) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return userInfoMapper.selectOne(queryWrapper);
    }


    @Override
    public User findUserByPhone(String phone) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("phone", phone));
    }

    @Override
    public User register(User user) {
        userMapper.insert(user);
        Integer userId = user.getUserId();
        preferService.register(userId);
        return user;

    }
}
