package com.icecream.shares.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icecream.shares.mapper.NoticeMapper;
import com.icecream.shares.pojo.Notice;
import com.icecream.shares.service.NoticeService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dqbryant
 * @create 2020/11/17 15:53
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Override
    public List<Notice> getNoticesById(Integer userId) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accept_id", userId);
        return baseMapper.selectList(queryWrapper);
    }
}
