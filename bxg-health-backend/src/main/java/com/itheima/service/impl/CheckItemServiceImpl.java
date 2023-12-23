package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.QueryPageBean;
import com.itheima.dao.CheckGroupCheckItemMapper;
import com.itheima.dao.CheckItemMapper;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CheckItemServiceImpl implements CheckItemService {
    @Resource
    private CheckItemMapper checkItemMapper;

    @Resource
    private CheckGroupCheckItemMapper checkGroupCheckItemMapper;

    /**
     * 新增检查项
     *
     * @param checkItem 检查项信息
     */
    @Override
    public void add(CheckItem checkItem) {
        checkItemMapper.insert(checkItem);
    }

    /**
     * 根据id删除检查项
     *
     * @param id 检查项id
     */
    @Override
    public void deleteById(Integer id) {
        checkItemMapper.deleteById(id);
    }

    /**
     * 根据检查组id查询检查项id集合
     *
     * @param checkgroupId 检查组id
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer checkgroupId) {
        return checkGroupCheckItemMapper.findCheckItemIdsByCheckGroupId(checkgroupId);
    }

    /**
     * 编辑检查项
     *
     * @param checkItem 检查项信息
     */
    @Override
    public void update(@RequestBody CheckItem checkItem) {
        checkItemMapper.update(checkItem);
    }

    /**
     * 分页查询检查项
     *
     * @param queryPageBean 分页条件
     * @return 分页结果
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<CheckItem> page = checkItemMapper.list(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 查询所有检查项
     *
     * @return 检查项集合
     */
    @Override
    public List<CheckItem> findAll() {
        return checkItemMapper.selectAll();
    }

    /**
     * 根据id查询检查项
     *
     * @param id 检查项id
     * @return 检查项信息
     */
    @Override
    public CheckItem findById(Integer id) {
        return checkItemMapper.selectById(id);
    }
}
