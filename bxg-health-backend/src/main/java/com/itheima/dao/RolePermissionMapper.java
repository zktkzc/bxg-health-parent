package com.itheima.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RolePermissionMapper {
    @Select("select * from t_role_permission where role_id = #{roleId}")
    List<Integer> selectByRoleId(Integer roleId);
}
