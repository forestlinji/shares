package com.icecream.shares.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author dqbryant
 * @create 2020/11/16 21:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    private Integer userId;
    private String username;
    private boolean sex;
    private Date birth;
    private String userSign;
}
