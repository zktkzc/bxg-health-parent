package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.QueryPageBean;
import com.itheima.dao.CheckGroupCheckItemMapper;
import com.itheima.dao.CheckGroupMapper;
import com.itheima.dao.CheckItemMapper;
import com.itheima.dao.SetmealCheckgroupMapper;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckGroupServiceImpl implements CheckGroupService {
    @Resource
    private CheckGroupMapper checkGroupMapper;
    @Resource
    private CheckGroupCheckItemMapper checkGroupCheckItemMapper;
    @Resource
    private SetmealCheckgroupMapper setmealCheckgroupMapper;
    @Resource
    private CheckItemMapper checkItemMapper;

    /**
     * 根据id删除检查组
     *
     * @param id 检查组id
     */
    @Override
    @Transactional
    public void deleteById(Integer id) {
        // 删除检查组与检查项关系
        checkGroupCheckItemMapper.deleteByCheckGroupId(id);
        // 删除检查组
        checkGroupMapper.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<CheckGroup> page = checkGroupMapper.list(queryPageBean.getQueryString());
        for (CheckGroup checkGroup : page) {
            List<Integer> checkItemIds = checkGroupCheckItemMapper.findCheckItemIdsByCheckGroupId(checkGroup.getId());
            List<CheckItem> checkItems = new ArrayList<>();
            for (Integer checkItemId : checkItemIds) {
                CheckItem checkItem = checkItemMapper.selectById(checkItemId);
                checkItems.add(checkItem);
            }
            checkGroup.setCheckItems(checkItems);
        }
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 根据套餐id查询关联的检查组id
     *
     * @param seatmealId 套餐id
     * @return
     */
    @Override
    public List<Integer> findCheckgroupIdsBySetmealId(Integer seatmealId) {
        return setmealCheckgroupMapper.selectBySetmealId(seatmealId);
    }

    /**
     * 编辑检查组
     *
     * @param checkitemIds
     * @param checkGroup
     */
    @Override
    @Transactional
    public void edit(List<Integer> checkitemIds, CheckGroup checkGroup) {
        checkGroupCheckItemMapper.deleteByCheckGroupId(checkGroup.getId());
        checkGroupCheckItemMapper.insertBatch(checkGroup.getId(), checkitemIds);
        checkGroupMapper.update(checkGroup);
    }

    /**
     * 根据id查询检查组
     *
     * @param id
     * @return
     */
    @Override
    public CheckGroup findById(Integer id) {
        CheckGroup checkGroup = checkGroupMapper.selectById(id);
        List<Integer> checkItemIds = checkGroupCheckItemMapper.findCheckItemIdsByCheckGroupId(checkGroup.getId());
        List<CheckItem> checkItems = new ArrayList<>();
        for (Integer checkItemId : checkItemIds) {
            CheckItem checkItem = checkItemMapper.selectById(checkItemId);
            checkItems.add(checkItem);
        }
        checkGroup.setCheckItems(checkItems);
        return checkGroup;
    }

    /**
     * 查询所有检查组
     *
     * @return
     */
    @Override
    public List<CheckGroup> findAll() {
        return checkGroupMapper.list(null);
    }

    /**
     * 新增检查组
     *
     * @param ids 检查项id集合
     */
    @Override
    @Transactional
    public void add(List<Integer> ids, CheckGroup checkGroup) {
        // 新增检查组
        checkGroupMapper.insert(checkGroup);
        Integer checkgroupId = checkGroup.getId();
        // 新增检查组与检查项关系
        checkGroupCheckItemMapper.insertBatch(checkgroupId, ids);
    }
}
