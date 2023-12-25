package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CheckGroupMapper {
    /**
     * 新增检查组
     */
    void insert(CheckGroup checkGroup);

    /**
     * 根据id删除检查组
     */
    @Delete("delete from t_checkgroup where id = #{id}")
    void deleteById(Integer id);

    /**
     * 分页查询
     *
     * @param queryString
     * @return
     */
    Page<CheckGroup> list(@Param("queryString") String queryString);

    /**
     * 根据id查询检查组
     *
     * @param id
     * @return
     */
    @Select("select * from t_checkgroup where id = #{id}")
    CheckGroup selectById(Integer id);

    /**
     * 编辑检查组
     *
     * @param checkGroup
     */
    void update(CheckGroup checkGroup);
}
