package com.icecream.shares.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode {

    SUCCESS(200,"成功"),
    ERROR(500,"服务器繁忙"),
    ERRORMETHOD(501,"接口调用方式错误"),
    UNVALIDPARAMS(201,"参数不合法"),
    WRONGINFO(202,"激活码错误"),
    EXISTS(204,"已经位于收藏中"),
    WRONGCODE(202,"验证码错误或过期"),
    REGISTER(210,"成功登录，但该用户为第一次登录"),
    TOOOFTEN(203,"频繁请求"),
    UNLOGIN(403,"未登录或权限不足"),

    //    WRONGJWT(204,"token错误或已过期"),
//    BANADMIN(205,"不能封禁管理员"),
//    SELFDELETE(206,"不能解除超级管理员的管理权限"),
//    USEDUSERNAME(301,"用户名已存在"),
//    USEDEMAIL(302,"邮箱已存在"),
//    REPORTED(304,"已举报"),
//    HASBANED(410,"您已被对方拉黑"),
    WRONGFORMAT(601,"文件格式错误"),
    BIGFILE(201,"文件过大");
    //操作代码
    int code;
    //提示信息
    String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
