package com.itheima.dao;

import com.itheima.pojo.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealMapper {
    /**
     * 查询所有套餐信息
     *
     * @return
     */
    @Select("select * from t_setmeal")
    List<Setmeal> selectAll();

    /**
     * 根据id查询套餐详情
     *
     * @param id
     * @return
     */
    @Select("select * from t_setmeal where id = #{id}")
    Setmeal selectById(Integer id);
}
