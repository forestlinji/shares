package com.icecream.shares.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icecream.shares.mapper.UserInfoMapper;
import com.icecream.shares.mapper.UserMapper;
import com.icecream.shares.pojo.User;
import com.icecream.shares.pojo.UserInfo;
import com.icecream.shares.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dqbryant
 * @create 2020/11/16 21:27
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements com.icecream.shares.service.UserInfoService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserInfo getUserInfoByUsername(String username) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return baseMapper.selectOne(queryWrapper);
    }
}
