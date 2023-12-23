package com.itheima.service;

import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    /**
     * 新增检查项
     *
     * @param checkItem 检查项信息
     */
    void add(CheckItem checkItem);

    /**
     * 根据id删除检查项
     *
     * @param id 检查项id
     */
    void deleteById(Integer id);

    /**
     * 根据检查组id查询检查项id集合
     *
     * @param checkgroupId 检查组id
     * @return 检查项id集合
     */
    List<Integer> findCheckItemIdsByCheckGroupId(Integer checkgroupId);

    /**
     * 编辑检查项
     *
     * @param checkItem 检查项信息
     */
    void update(CheckItem checkItem);

    /**
     * 分页查询检查项
     *
     * @param queryPageBean 分页条件
     * @return 分页结果
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 查询所有检查项
     *
     * @return 检查项集合
     */
    List<CheckItem> findAll();

    /**
     * 根据id查询检查项
     *
     * @param id 检查项id
     * @return 检查项信息
     */
    Object findById(Integer id);
}
