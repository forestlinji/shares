package com.icecream.shares.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostStatusVo {
    private Boolean good = false;
    private Boolean bad = false;
    private Boolean collect = false;
}
