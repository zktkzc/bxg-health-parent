package com.itheima.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CheckGroupCheckItemMapper {

    /**
     * 根据检查组id查询检查项id集合
     *
     * @param checkgroupId 检查组id
     * @return 检查项id集合
     */
    @Select("select checkitem_id from t_checkgroup_checkitem where checkgroup_id = #{checkgroupId}")
    List<Integer> findCheckItemIdsByCheckGroupId(Integer checkgroupId);
}
