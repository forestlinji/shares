package com.icecream.shares.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icecream.shares.pojo.Concern;

/**
 * @author dqbryant
 * @create 2020/11/16 20:45
 */
public interface ConcernService extends IService<Concern> {
    int addConcern(Concern concern);
    QueryWrapper<Concern> cancelConcern(Integer concernUserId, Integer concernedUserId);
}
