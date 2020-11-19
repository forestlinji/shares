package com.icecream.shares.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConcernList {
    private Integer userId;
    private String  userName;
    private String headLink;
    private Integer shareNum;
    private Integer fanNum;
}
