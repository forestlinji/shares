package com.icecream.shares.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icecream.shares.mapper.ConcernMapper;
import com.icecream.shares.mapper.UserInfoMapper;
import com.icecream.shares.pojo.Concern;
import com.icecream.shares.pojo.UserInfo;
import com.icecream.shares.pojo.ConcernList;
import com.icecream.shares.service.ConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author dqbryant
 * @create 2020/11/16 20:47
 */
@Service
public class ConcernServiceImpl extends ServiceImpl<ConcernMapper, Concern> implements ConcernService  {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public int addConcern(Concern concern) {
        if(baseMapper.insertOrIgnore(concern) == 1) {
            return userInfoMapper.update(null, new UpdateWrapper<UserInfo>().setSql("fan_num = fan_num + 1").eq("user_id", concern.getConcernedUserId()));
        }
        return 0;
    }

    @Override
    public int cancelConcern(Concern concern) {

        if(baseMapper.deleteConcern(concern) == 1){
            return userInfoMapper.update(null, new UpdateWrapper<UserInfo>().setSql("fan_num = fan_num - 1").eq("user_id", concern.getConcernedUserId()));
        }
        return 0;
    }
    @Override
    public List<ConcernList> getConcern(Integer userId)
    {
        return baseMapper.getConcern(userId);
    }
    @Override
    public List<ConcernList> getConcerned(Integer userId)
    {
        return baseMapper.getConcerned(userId);
    }
//
//    @Override
//    public QueryWrapper<Concern> cancelConcern(Integer concernUserId, Integer concernedUserId) {
//        QueryWrapper<Concern> queryWrapper = new QueryWrapper<>();
//
//        queryWrapper.eq("concern_user_id",concernUserId).eq("concerned_user_id",concernedUserId);
//
//        return queryWrapper;
//    }
}
