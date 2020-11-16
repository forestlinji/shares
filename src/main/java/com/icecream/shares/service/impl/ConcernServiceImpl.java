package com.icecream.shares.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icecream.shares.mapper.ConcernMapper;
import com.icecream.shares.pojo.Concern;
import com.icecream.shares.service.ConcernService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dqbryant
 * @create 2020/11/16 20:47
 */
@Service
public class ConcernServiceImpl extends ServiceImpl<ConcernMapper, Concern> implements ConcernService  {

}
