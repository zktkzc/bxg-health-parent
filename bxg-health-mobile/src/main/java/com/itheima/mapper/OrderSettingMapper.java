package com.itheima.mapper;

import com.itheima.pojo.OrderSetting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface OrderSettingMapper {
    @Select("select * from t_ordersetting where orderDate = #{orderDate}")
    OrderSetting selectByOrderDate(Date orderDate);

    @Insert("insert into t_ordersetting (orderDate,number,reservations) values (#{orderDate},#{number},#{reservations})")
    void insert(OrderSetting orderSetting);

    void update(OrderSetting orderSetting);
}
