package com.icecream.shares.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author dqbryant
 * @create 2020/11/18 19:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostVo {
    private Integer postId;
    private String postTitle;
    private String coverLink;
    private Timestamp releaseTime;
}
