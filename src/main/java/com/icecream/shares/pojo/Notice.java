package com.icecream.shares.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author dqbryant
 * @create 2020/11/17 15:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    @TableId(type = IdType.AUTO)
    private Integer noticeId;
    private Integer acceptId;
    private String headline;
    private String content;
    private Timestamp releaseTime;
}
