package com.icecream.shares.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 17918
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchPostVo {
    private Integer postId;
    private String title;
    private String content;
    private String coverLink;

}
