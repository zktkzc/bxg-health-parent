package com.itheima.mapper;

import com.itheima.pojo.CheckItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CheckitemMapper {
    /**
     * 根据id查询检查项信息
     */
    @Select("select * from t_checkitem where id = #{id}")
    CheckItem findById(Integer id);
}
