package com.itheima.service;

import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;

import java.util.List;

public interface SetMealService {
    /**
     * 新增体检套餐
     *
     * @param checkgroupIds
     * @param setmeal
     */
    void add(List<Integer> checkgroupIds, Setmeal setmeal);

    /**
     * 编辑套餐
     *
     * @param checkgroupIds
     * @param setmeal
     */
    void edit(List<Integer> checkgroupIds, Setmeal setmeal);

    /**
     * 根据id查询套餐
     *
     * @param id
     * @return
     */
    Setmeal findById(Integer id);

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);
}
