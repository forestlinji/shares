package com.icecream.shares.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author dqbryant
 * @create 2020/11/18 15:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @TableId(type = IdType.AUTO)
    private Integer commentId;
    private Integer postId;
    private Integer commentUserId;
    private String content;
    private Timestamp releaseTime;
    @TableLogic
    @JsonIgnore
    private Integer deleted;

    public Comment(Integer postId, Integer commentUserId, String content, Timestamp releaseTime) {
        this.postId = postId;
        this.commentUserId = commentUserId;
        this.content = content;
        this.releaseTime = releaseTime;
    }
}
