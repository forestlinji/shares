package com.icecream.shares.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icecream.shares.pojo.Concern;

/**
 * @author dqbryant
 * @create 2020/11/16 20:45
 */
public interface ConcernService extends IService<Concern> {
    /**
     * 添加关注
     * @param concern 关注的对象
     * @return 返回数据库影响的行数
     */
    int addConcern(Concern concern);
//    QueryWrapper<Concern> cancelConcern(Integer concernUserId, Integer concernedUserId);

    /**
     * 取消关注
     * @param concern 关注的对象
     * @return 返回数据库影响的行数
     */
    int cancelConcern(Concern concern);
}
