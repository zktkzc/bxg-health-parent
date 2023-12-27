package com.itheima.service.impl;

import com.itheima.dao.SetmealMapper;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Resource
    private SetmealMapper setmealMapper;

    /**
     * 根据id查询套餐详情
     *
     * @param id
     * @return
     */
    @Override
    public Setmeal findById(Integer id) {
        return setmealMapper.selectById(id);
    }

    /**
     * 获取所有套餐信息
     *
     * @return
     */
    @Override
    public Object getSetmeal() {
        return setmealMapper.selectAll();
    }
}
