package com.icecream.shares.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WechatLogin {
    String openid;
    String session_key;
    String unionid;
    Integer errcode;
    String errmsg;
}
