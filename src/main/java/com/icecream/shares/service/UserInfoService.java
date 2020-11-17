package com.icecream.shares.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.icecream.shares.pojo.User;
import com.icecream.shares.pojo.UserInfo;
import com.icecream.shares.vo.UserInfoVo;

/**
 * @author dqbryant
 * @create 2020/11/16 21:26
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户
     */
    UserInfo getUserInfoByUsername(String username);

    /**
     * 更新用户信息
     * @param userInfoVo 更新用户信息
     * @return 是否成功
     */
    boolean update(UserInfoVo userInfoVo);
}
