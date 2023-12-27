package com.itheima.mapper;

import com.itheima.pojo.CheckGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CheckGroupMapper {
    /**
     * 根据id查询检查组信息
     */
    @Select("select * from t_checkgroup where id = #{id}")
    CheckGroup findById(Integer id);
}
