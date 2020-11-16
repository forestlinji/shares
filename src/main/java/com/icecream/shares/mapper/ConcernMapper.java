package com.icecream.shares.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.icecream.shares.pojo.Concern;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author dqbryant
 * @create 2020/11/16 20:44
 */
@Repository
@Mapper
public interface ConcernMapper extends BaseMapper<Concern> {
}