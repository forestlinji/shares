package com.icecream.shares.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prefer {
    @TableId
    private Integer userId;
    private Integer study;
    private Integer food;
    private Integer cloth;
    private Integer room;
    private Integer others;
}
