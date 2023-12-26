package com.itheima.dao;

import com.itheima.pojo.OrderSetting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface OrderSettingMapper {
    /**
     * 根据日期修改可预约人数
     */
    @Update("update t_ordersetting set number = #{number} where orderDate = #{orderDate}")
    void updateNumberByOrderDate(OrderSetting orderSetting);

    /**
     * 根据年月查询对应的预约设置信息
     */
    @Select("select * from t_ordersetting where orderDate like concat(#{month},'%') order by orderDate asc")
    List<OrderSetting> selectByMonth(String month);

    @Select("select * from t_ordersetting where orderDate = #{date}")
    OrderSetting selectByDate(LocalDate date);

    @Insert("insert into t_ordersetting(orderDate,number) values(#{orderDate},#{number})")
    void insert(OrderSetting orderSetting);
}
