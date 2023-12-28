package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.common.vo.OrderVO;
import com.itheima.pojo.Order;
import com.itheima.pojo.SetmealReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

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

    @Select("select setmeal_id setmealId,count(*) value from t_order group by setmeal_id order by value desc")
    List<SetmealReport> countSetmeal();

    Integer countByRegDateAndOrderStatus(@Param("today") LocalDate today, @Param("orderStatus") String orderStatus);

    Integer countByRegDateAndOrderStatusOfWeek(@Param("begin") LocalDate begin, @Param("end") LocalDate end, @Param("orderStatus") String orderStatus);

    Integer countByRegDateAndOrderStatusOfMonth(@Param("month") String month, @Param("orderStatus") String orderStatus);

    @Select("select count(*) from t_order")
    double countSetmealTotal();
}
