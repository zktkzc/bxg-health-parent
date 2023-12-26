package com.itheima.service;

import com.itheima.common.entity.PageResult;
import com.itheima.common.entity.QueryPageBean;
import com.itheima.pojo.Order;

public interface OrderService {
    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 修改状态
     *
     * @param order
     */
    void update(Order order);
}
