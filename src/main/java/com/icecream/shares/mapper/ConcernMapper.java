package com.icecream.shares.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icecream.shares.pojo.Concern;
import com.icecream.shares.pojo.ConcernList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Select("select user_id,username,head_link,share_num,fan_num from concern,user_info" +
            " where user_id = concerned_user_id and concern_user_id = #{userId}")
    List<ConcernList> getConcern(Integer userId);
    @Select("select user_id,username,head_link,share_num,fan_num from concern,user_info" +
            " where user_id = concern_user_id and concerned_user_id = #{userId}")
    List<ConcernList> getConcerned(Integer userId);
}
