package com.itheima.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRoleLMapper {
    @Select("select * from t_user_role where user_id = #{id}")
    List<Integer> selectByUserId(Integer id);
}
