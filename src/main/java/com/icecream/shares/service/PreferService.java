package com.icecream.shares.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.icecream.shares.pojo.Prefer;

public interface PreferService extends IService<Prefer> {
    // 注册时根据userId初始化prefer
    void register(Integer userId);
}
