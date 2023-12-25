package com.itheima.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 批量新增套餐与检查组关系
     */
    void insertBatch(@Param("setmealId") Integer setmealId, @Param("checkgroupIds") List<Integer> checkgroupIds);

    /**
     * 根据套餐id删除套餐与检查组的对应关系
     */
    @Delete("delete from t_setmeal_checkgroup where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Integer setmealId);
}
