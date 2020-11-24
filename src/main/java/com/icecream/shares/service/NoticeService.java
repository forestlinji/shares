package com.icecream.shares.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.icecream.shares.pojo.Notice;
import com.icecream.shares.pojo.Post;

import java.util.List;

/**
 * @author dqbryant
 * @create 2020/11/17 15:53
 */
public interface NoticeService extends IService<Notice> {
    /**
     * 获取用户的通知
     * @param userId 用户id
     * @return
     */
    List<Notice> getNoticesById(Integer userId);

    void sendRegister(Integer userId);

    void sendCheck(Post post);
}
