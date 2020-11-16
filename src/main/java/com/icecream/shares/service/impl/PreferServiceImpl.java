package com.icecream.shares.service.impl;

import com.icecream.shares.mapper.PreferMapper;

import com.icecream.shares.pojo.Prefer;
import com.icecream.shares.service.PreferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreferServiceImpl implements PreferService {
    @Autowired
    public PreferMapper preferMapper;

    @Override
    public void register(Integer userId) {
        Prefer prefer = new Prefer();
        prefer.setUserId(userId);
        preferMapper.insert(prefer);
    }
}
