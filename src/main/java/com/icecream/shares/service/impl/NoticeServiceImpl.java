package com.icecream.shares.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icecream.shares.mapper.NoticeMapper;
import com.icecream.shares.pojo.Notice;
import com.icecream.shares.pojo.Post;
import com.icecream.shares.service.NoticeService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author dqbryant
 * @create 2020/11/17 15:53
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public List<Notice> getNoticesById(Integer userId) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accept_id", userId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public void sendRegister(Integer userId) {
        Notice notice = new Notice();
        notice.setAcceptId(userId);
        notice.setReleaseTime(new Timestamp(System.currentTimeMillis()));
        notice.setHeadline("注册成功");
        notice.setContent("注册成功，快快开始寻找好物吧");
        noticeMapper.insert(notice);
    }

    @Override
    public void sendCheck(Post post) {
        Notice notice = new Notice();
        notice.setAcceptId(post.getReleaseId());
        notice.setReleaseTime(new Timestamp(System.currentTimeMillis()));
        notice.setHeadline("审核通过");
        notice.setContent("您发布的分享帖《"+post.getTitle()+"》已通过审核");
        noticeMapper.insert(notice);
    }
}
