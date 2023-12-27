package com.itheima.service.impl;

import com.itheima.mapper.*;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Resource
    private SetmealMapper setmealMapper;
    @Resource
    private SetmealCheckgroupMapper setmealCheckgroupMapper;
    @Resource
    private CheckGroupMapper checkGroupMapper;
    @Resource
    private CheckgroupCheckitemMapper checkgroupCheckitemMapper;
    @Resource
    private CheckitemMapper checkitemMapper;

    /**
     * 根据id查询套餐详情
     *
     * @param id
     * @return
     */
    @Override
    public Setmeal findById(Integer id) {
        Setmeal setmeal = setmealMapper.selectById(id);
        List<Integer> checkgroupIds = setmealCheckgroupMapper.findCheckgroupIdsBySetmealId(id);
        List<CheckGroup> checkGroupList = new ArrayList<>();
        List<CheckItem> checkItemList = new ArrayList<>();
        for (Integer checkgroupId : checkgroupIds) {
            CheckGroup checkgroup = checkGroupMapper.findById(checkgroupId);
            List<Integer> checkitemIds = checkgroupCheckitemMapper.findCheckitemIdsByCheckgroupId(checkgroupId);
            for (Integer checkitemId : checkitemIds) {
                CheckItem checkitem = checkitemMapper.findById(checkitemId);
                checkItemList.add(checkitem);
            }
            checkgroup.setCheckItems(checkItemList);
            checkGroupList.add(checkgroup);
        }
        setmeal.setCheckGroups(checkGroupList);
        return setmeal;
    }

    /**
     * 获取所有套餐信息
     *
     * @return
     */
    @Override
    public List<Setmeal> getSetmeal() {
        List<Setmeal> setmeals = setmealMapper.selectAll();
        for (Setmeal setmeal : setmeals) {
            List<Integer> checkgroupIds = setmealCheckgroupMapper.findCheckgroupIdsBySetmealId(setmeal.getId());
            List<CheckGroup> checkGroupList = new ArrayList<>();
            List<CheckItem> checkItemList = new ArrayList<>();
            for (Integer checkgroupId : checkgroupIds) {
                CheckGroup checkgroup = checkGroupMapper.findById(checkgroupId);
                List<Integer> checkitemIds = checkgroupCheckitemMapper.findCheckitemIdsByCheckgroupId(checkgroupId);
                for (Integer checkitemId : checkitemIds) {
                    CheckItem checkitem = checkitemMapper.findById(checkitemId);
                    checkItemList.add(checkitem);
                }
                checkgroup.setCheckItems(checkItemList);
                checkGroupList.add(checkgroup);
            }
            setmeal.setCheckGroups(checkGroupList);
        }
        return setmeals;
    }
}
