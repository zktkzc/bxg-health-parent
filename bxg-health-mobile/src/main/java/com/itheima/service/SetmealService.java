package com.itheima.service;

import com.itheima.pojo.Setmeal;

public interface SetmealService {
    /**
     * 获取所有套餐信息
     *
     * @return
     */
    Object getSetmeal();

    /**
     * 根据id查询套餐详情
     *
     * @param id
     * @return
     */
    Setmeal findById(Integer id);
}
