package com.icecream.shares.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icecream.shares.mapper.UserInfoMapper;
import com.icecream.shares.mapper.UserMapper;
import com.icecream.shares.pojo.User;
import com.icecream.shares.pojo.UserInfo;
import com.icecream.shares.service.UserInfoService;
import com.icecream.shares.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dqbryant
 * @create 2020/11/16 21:27
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserInfo getUserInfoByUsername(String username) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return baseMapper.selectOne(queryWrapper);
    }
    @Transactional(rollbackFor = {})
    @Override
    public boolean update(UserInfoVo userInfoVo) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("user_id", userInfoVo.getUserId()).set("username", userInfoVo.getUsername());
        UpdateWrapper<UserInfo> userInfoUpdateWrapper = new UpdateWrapper<>();
        userInfoUpdateWrapper.eq("user_id",userInfoVo.getUserId()).set("username", userInfoVo.getUsername())
                .set("sex", userInfoVo.isSex()).set("birth",userInfoVo.getBirth()).set("userSign",userInfoVo.getUserSign());
        return userMapper.update(null, userUpdateWrapper) == 1 && baseMapper.update(null, userInfoUpdateWrapper) == 1;
    }
}
