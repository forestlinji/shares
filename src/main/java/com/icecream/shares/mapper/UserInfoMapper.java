package com.icecream.shares.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icecream.shares.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author dqbryant
 * @create 2020/11/16 20:25
 */
@Mapper
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
