package com.itheima.dao;

import com.itheima.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PermissionMapper {
    @Select("select * from t_permission where id = #{permissionId}")
    Permission selectById(Integer permissionId);
}
