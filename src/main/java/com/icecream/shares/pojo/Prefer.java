package com.icecream.shares.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prefer {
    @TableId(type = IdType.INPUT)
    private Integer userId;
    private Integer study;
    private Integer food;
    private Integer cloth;
    private Integer room;
    private Integer others;
}
