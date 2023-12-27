package com.itheima.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealCheckgroupMapper {
    /**
     * 根据套餐id查询检查组id
     */
    @Select("select checkgroup_id from t_setmeal_checkgroup where setmeal_id = #{id}")
    List<Integer> findCheckgroupIdsBySetmealId(Integer id);
}
