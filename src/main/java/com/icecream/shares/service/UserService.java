package com.icecream.shares.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.icecream.shares.pojo.UserInfo;
import com.icecream.shares.pojo.User;

import com.icecream.shares.vo.ChangePasswordVo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends IService<User> {


    //根据手机号查询用户
    User findUserByPhone(String phone);

    /**
     * 根据openId查询用户
     * @param openId
     * @return
     */
    User findUserByOpenId(String openId);

    // 用户注册
    User register(User user);

    /**
     * 根据用户id获取角色
     * @param userId
     * @return
     */
    List<String> getRolesByUserId(Integer userId);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 根据用户id获取信息
     * @param userId
     * @return
     */
    public UserInfo getUserInfo(Integer userId);

    /**
     * 根据用户id获取权限信息
     * @param userId
     */
    User findUserByUserId(int userId);

    /**
     * 修改密码
     * @param user
     * @param changePasswordVo
     * @return
     */
    boolean changePassword(User user, ChangePasswordVo changePasswordVo);

    /**
     * 更新user表
     * @param user
     */
    void updateUser(User user);


}
