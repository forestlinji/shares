package com.icecream.shares.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dqbryant
 * @create 2020/11/19 10:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecideVo {
    private List<Integer> postIds;
    private Integer criterion1;
    private Integer criterion2;
    private Integer criterion3;
}
