package com.icecream.shares.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icecream.shares.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author dqbryant
 * @create 2020/11/17 15:53
 */
@Repository
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
}
