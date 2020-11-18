package com.icecream.shares.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.icecream.shares.pojo.Comment;
import com.icecream.shares.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author dqbryant
 * @create 2020/11/18 15:11
 */
@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("select comment_id,content,release_time,user_id,username,head_link " +
            "from comment c JOIN user_info on comment_user_id = user_info.user_id where post_id = #{postId} and deleted = 0")
    IPage<CommentVo> selectPage(Page<CommentVo> page, Integer postId);
}
