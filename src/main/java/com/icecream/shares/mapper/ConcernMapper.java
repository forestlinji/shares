package com.icecream.shares.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.icecream.shares.pojo.Concern;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author dqbryant
 * @create 2020/11/16 20:44
 */
@Repository
@Mapper
public interface ConcernMapper extends BaseMapper<Concern> {
    @Insert("insert IGNORE into concern values(#{concernUserId}, #{concernedUserId})")
    int insertOrIgnore(Concern concern);

    @Delete("delete from concern where concern_user_id = #{concernUserId} and concerned_user_id = #{concernedUserId}")
    int deleteConcern(Concern concern);
}
