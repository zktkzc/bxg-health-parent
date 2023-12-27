package com.itheima.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CheckgroupCheckitemMapper {
    /**
     * 根据检查组id查询检查项id
     */
    @Select("select checkitem_id from t_checkgroup_checkitem where checkgroup_id = #{checkgroupId}")
    List<Integer> findCheckitemIdsByCheckgroupId(Integer checkgroupId);
}
