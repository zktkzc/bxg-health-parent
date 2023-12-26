package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.common.vo.OrderVO;
import com.itheima.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper {
    /**
     * 分页查询
     *
     * @param queryString
     * @return
     */
    Page<OrderVO> list(@Param("queryString") String queryString);

    /**
     * 修改状态
     *
     * @param order
     */
    @Update("update t_order set orderStatus = #{orderStatus} where id = #{id}")
    void update(Order order);
}
