package com.icecream.shares.pojo;

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
    private Integer concernUserId;
    private Integer concernedUserId;
}
