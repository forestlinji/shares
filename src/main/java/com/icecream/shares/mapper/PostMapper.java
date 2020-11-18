package com.icecream.shares.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icecream.shares.pojo.Post;
import com.icecream.shares.vo.PostVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostMapper extends BaseMapper<Post> {
    /**
     * 查询某个用户某个状态的帖子
     * @param userId 用户id
     * @param type 帖子状态
     * @return 帖子集合
     */
    @Select("select post_id, title, cover_link, release_time from post where release_id = #{userId} and check_state = #{type} and deleted = 0")
    List<PostVo> getPostProcess(Integer userId, Integer type);

    /**
     * 获取用户执行过某些操作的帖子信息
     * @param userId 用户id
     * @param type 操作类型
     * @return 帖子信息
     */
    @Select("select post_id, title, cover_link from post where post_id in (select post_id from post_operation where operator_id = #{userId} and operation_type = #{type})")
    List<PostVo> getCollections(Integer userId, Integer type);
}
