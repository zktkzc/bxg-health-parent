package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.QueryPageBean;
import com.itheima.dao.CheckGroupMapper;
import com.itheima.dao.SetmealCheckgroupMapper;
import com.itheima.dao.SetmealMapper;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetMealService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SetMealServiceImpl implements SetMealService {
    @Resource
    private SetmealMapper setmealMapper;
    @Resource
    private SetmealCheckgroupMapper setmealCheckgroupMapper;
    @Resource
    private CheckGroupMapper checkGroupMapper;

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<Setmeal> page = setmealMapper.list(queryPageBean.getQueryString());
        for (Setmeal setmeal : page) {
            List<Integer> checkgroupIds = setmealCheckgroupMapper.selectBySetmealId(setmeal.getId());
            List<CheckGroup> checkGroups = new ArrayList<>();
            for (Integer checkgroupId : checkgroupIds) {
                CheckGroup checkGroup = checkGroupMapper.selectById(checkgroupId);
                checkGroups.add(checkGroup);
            }
            setmeal.setCheckGroups(checkGroups);
        }
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 根据id查询套餐
     *
     * @param id
     * @return
     */
    @Override
    public Setmeal findById(Integer id) {
        Setmeal setmeal = setmealMapper.selectById(id);
        List<Integer> checkgroupIds = setmealCheckgroupMapper.selectBySetmealId(id);
        List<CheckGroup> checkGroups = new ArrayList<>();
        for (Integer checkgroupId : checkgroupIds) {
            CheckGroup checkGroup = checkGroupMapper.selectById(id);
            checkGroups.add(checkGroup);
        }
        return setmeal;
    }

    /**
     * 编辑套餐
     *
     * @param checkgroupIds
     * @param setmeal
     */
    @Override
    @Transactional
    public void edit(List<Integer> checkgroupIds, Setmeal setmeal) {
        setmealCheckgroupMapper.deleteBySetmealId(setmeal.getId());
        setmealMapper.update(setmeal);
        setmealCheckgroupMapper.insertBatch(setmeal.getId(), checkgroupIds);
    }

    /**
     * 新增体检套餐
     *
     * @param checkgroupIds
     * @param setmeal
     */
    @Override
    @Transactional
    public void add(List<Integer> checkgroupIds, Setmeal setmeal) {
        setmealMapper.insert(setmeal);
        setmealCheckgroupMapper.insertBatch(setmeal.getId(), checkgroupIds);
    }
}
