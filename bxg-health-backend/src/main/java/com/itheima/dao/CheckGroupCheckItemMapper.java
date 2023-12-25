package com.itheima.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 新增检查组与检查项关系
     *
     * @param checkgroupId 检查组id
     * @param checkitemIds 检查项id集合
     */
    void insertBatch(@Param("checkgroupId") Integer checkgroupId, @Param("checkitemIds") List<Integer> checkitemIds);

    /**
     * 根据检查组id删除检查组与检查项关系
     *
     * @param id 检查组id
     */
    @Delete("delete from t_checkgroup_checkitem where checkgroup_id = #{id}")
    void deleteByCheckGroupId(Integer id);
}
