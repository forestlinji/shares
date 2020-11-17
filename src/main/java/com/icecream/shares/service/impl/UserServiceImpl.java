package com.icecream.shares.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icecream.shares.mapper.RoleMapper;
import com.icecream.shares.mapper.UserInfoMapper;
import com.icecream.shares.pojo.UserInfo;

import com.icecream.shares.mapper.UserMapper;
import com.icecream.shares.pojo.Prefer;
import com.icecream.shares.pojo.User;
import com.icecream.shares.service.PreferService;

import com.icecream.shares.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    public UserMapper userMapper;
    @Autowired
    public PreferService preferService;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    public RoleMapper roleMapper;

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
    public User findUserByOpenId(String openId) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("open_id", openId));

    }

    @Override
    public User register(User user) {
        userMapper.insert(user);
        Integer userId = user.getUserId();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        if(user.getUsername()!=null){
            userInfo.setUsername(user.getUsername());
        }
        userInfoMapper.insert(userInfo);
        preferService.register(userId);
        roleMapper.addRole(userId, 1);
        return user;

    }

    @Override
    public List<String> getRolesByUserId(Integer userId) {
        return roleMapper.getRolesByUserId(userId);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }


}
