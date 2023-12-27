package com.itheima.dao;

import com.itheima.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper {
    @Select("select * from t_role where id = #{roleId}")
    Role selectById(Integer roleId);
}
