package com.itheima.mapper;

import com.itheima.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper {
    /**
     * 根据id查询预约信息
     */
    @Select("select * from t_order where id = #{id}")
    Order findById(Integer id);

    /**
     * 体检预约
     *
     * @param order
     */
    void insert(Order order);
}
