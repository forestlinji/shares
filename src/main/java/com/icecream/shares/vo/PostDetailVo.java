package com.icecream.shares.vo;

import com.icecream.shares.pojo.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
public class PostDetailVo extends Post {
    private String username;
    private String headLink;

    public PostDetailVo(Integer postId, Integer releaseId, Timestamp releaseTime, String title, String content, Integer checkState, Integer deleted, String coverLink, String picLink, Integer beauty, Integer price, Integer type, Integer quality, Integer collectNum, Integer goodNum, Integer badNum, String username, String headLink) {
        super(postId, releaseId, releaseTime, title, content, checkState, deleted, coverLink, picLink, beauty, price, type, quality, collectNum, goodNum, badNum);
        this.username = username;
        this.headLink = headLink;
    }

    public PostDetailVo(String username, String headLink) {
        this.username = username;
        this.headLink = headLink;
    }

}
