package com.icecream.shares.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prefer {
    private Integer userId;
    private Integer study;
    private Integer food;
    private Integer cloth;
    private Integer room;
    private Integer others;
}
