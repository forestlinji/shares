package com.icecream.shares.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icecream.shares.pojo.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据userId查询对应的角色
     * @param userId
     * @return
     */
    @Select("select r.role_name from user_role ur,role r where ur.user_id = #{userId} and ur.role_id = r.role_id")
    List<String> getRolesByUserId(Integer userId);

    /**
     * 添加对应角色
     * @param userId
     * @param roleId
     */
    @Insert("insert into user_role values(#{userId}, #{roleId})")
    void addRole(Integer userId, Integer roleId);
}
