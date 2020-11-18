package com.icecream.shares.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author dqbryant
 * @create 2020/11/18 15:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {
    private Integer commentId;
    private String content;
    private Timestamp releaseTime;
    private Integer userId;
    private String username;
    private String headLink;
}
