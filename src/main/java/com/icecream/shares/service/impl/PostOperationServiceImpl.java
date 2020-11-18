package com.icecream.shares.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icecream.shares.mapper.NoticeMapper;
import com.icecream.shares.mapper.PostOperationMapper;
import com.icecream.shares.pojo.Notice;
import com.icecream.shares.pojo.PostOperation;
import com.icecream.shares.service.NoticeService;
import com.icecream.shares.service.PostOperationService;
import com.icecream.shares.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostOperationServiceImpl extends ServiceImpl<PostOperationMapper, PostOperation> implements PostOperationService {
    @Autowired
    public PostOperationMapper postOperationMapper;
    @Autowired
    public PostService postService;
}
