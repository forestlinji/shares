package com.icecream.shares.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icecream.shares.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author dqbryant
 * @create 2020/11/16 20:25
 */
@Mapper
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    @Update("update user_info set fan_num = fan_num + 1 where user_id = #{id}")
    int addFanNum(Integer userId);
    @Update("update user_info set fan_num = fan_num + 1 where user_id = #{id}")
    int addFanNum2(Integer userId);
}
