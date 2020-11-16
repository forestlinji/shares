package com.icecream.shares.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.icecream.shares.vo.UserInfoVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author dqbryant
 * @create 2020/11/16 19:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @TableId
    private Integer userId;
    private String username;
    private boolean sex;
    private Date birth;
    private String userSign;
    private String headLink;
    private Integer shareNum;
    private Integer fanNum;
    public void update(UserInfoVo userInfoVo){
        this.username = userInfoVo.getUsername();
        this.birth = userInfoVo.getBirth();
        this.sex = userInfoVo.isSex();
        this.userSign = userInfoVo.getUserSign();
    }
}
