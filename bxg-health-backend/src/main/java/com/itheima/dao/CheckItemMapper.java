package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CheckItemMapper {

    /**
     * 新增检查项
     *
     * @param checkItem 检查项信息
     */
    void insert(CheckItem checkItem);

    /**
     * 根据id删除检查项
     *
     * @param id 检查项id
     */
    @Delete("delete from t_checkitem where id = #{id}")
    void deleteById(Integer id);

    /**
     * 编辑检查项
     *
     * @param checkItem 检查项信息
     */
    void update(CheckItem checkItem);

    /**
     * 分页查询检查项
     *
     * @param queryString 查询条件
     * @return 分页结果
     */
    Page<CheckItem> list(@Param("queryString") String queryString);

    /**
     * 查询所有检查项
     *
     * @return 检查项集合
     */
    @Select("select * from t_checkitem order by id")
    List<CheckItem> selectAll();

    /**
     * 根据id查询检查项
     *
     * @param id 检查项id
     * @return 检查项信息
     */
    @Select("select * from t_checkitem where id = #{id}")
    CheckItem selectById(Integer id);
}
