package com.icecream.shares.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchPostVo {
    private long postId;
    private String title;
    private String content;
    private String coverLink;

}
