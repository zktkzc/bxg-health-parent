package com.itheima.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealCheckgroupMapper {
    /**
     * 根据套餐id查询关联的检查组id
     *
     * @param seatmealId 套餐id
     * @return
     */
    @Select("select checkgroup_id from t_setmeal_checkgroup where setmeal_id = #{seatmealId}")
    List<Integer> selectBySetmealId(Integer seatmealId);
}
