package com.icecream.shares.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    public Integer roleId;
    public String roleName;
    public String roleDesc;
}
