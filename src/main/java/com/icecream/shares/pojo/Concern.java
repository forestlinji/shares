package com.icecream.shares.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dqbryant
 * @create 2020/11/16 20:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Concern {
    @TableId
    private Integer concernUserId;
    @TableId
    private Integer concernedUserId;
}
