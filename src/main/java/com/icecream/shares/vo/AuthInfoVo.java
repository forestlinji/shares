package com.icecream.shares.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthInfoVo {
    private Integer userId;
    private String username;
    private String phone;
    private Timestamp registerTime;
    List<String> roles;
}
