package com.itheima.service;

import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    /**
     * 新增检查组
     *
     * @param ids 检查项id集合
     */
    void add(List<Integer> ids, CheckGroup checkGroup);

    /**
     * 根据id删除检查组
     *
     * @param id 检查组id
     */
    void deleteById(Integer id);

    /**
     * 根据套餐id查询关联的检查组id
     *
     * @param seatmealId 套餐id
     * @return
     */
    List<Integer> findCheckgroupIdsBySetmealId(Integer seatmealId);

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 编辑检查组
     *
     * @param checkitemIds
     * @param checkGroup
     */
    void edit(List<Integer> checkitemIds, CheckGroup checkGroup);

    /**
     * 查询所有检查组
     *
     * @return
     */
    List<CheckGroup> findAll();

    /**
     * 根据id查询检查组
     *
     * @param id
     * @return
     */
    CheckGroup findById(Integer id);
}
