package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetmealMapper {
    /**
     * 新增体检套餐
     *
     * @param setmeal
     */
    void insert(Setmeal setmeal);

    /**
     * 更新套餐信息
     *
     * @param setmeal
     */
    void update(Setmeal setmeal);

    /**
     * 根据套餐id查询套餐
     *
     * @param id
     * @return
     */
    @Select("select * from t_setmeal where id = #{id}")
    Setmeal selectById(Integer id);

    /**
     * 分页查询
     *
     * @param queryString
     * @return
     */
    Page<Setmeal> list(@Param("queryString") String queryString);

    @Select("select name from t_setmeal where id = #{setmealId}")
    String findNameById(Integer setmealId);
}
