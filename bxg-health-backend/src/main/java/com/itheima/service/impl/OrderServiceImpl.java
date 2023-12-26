package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.QueryPageBean;
import com.itheima.common.vo.OrderVO;
import com.itheima.dao.OrderMapper;
import com.itheima.pojo.Order;
import com.itheima.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    /**
     * 修改状态
     *
     * @param order
     */
    @Override
    public void update(Order order) {
        orderMapper.update(order);
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
        Page<OrderVO> page = orderMapper.list(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }
}
