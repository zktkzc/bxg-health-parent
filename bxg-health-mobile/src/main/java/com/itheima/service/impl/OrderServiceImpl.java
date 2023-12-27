package com.itheima.service.impl;

import com.itheima.common.dto.SubmitOrderDTO;
import com.itheima.common.vo.FindOrderByIdVO;
import com.itheima.mapper.MemberMapper;
import com.itheima.mapper.OrderMapper;
import com.itheima.mapper.SetmealMapper;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.Setmeal;
import com.itheima.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.ZoneId;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private SetmealMapper setmealMapper;

    /**
     * 体检预约
     *
     * @param submitOrderDTO
     */
    @Override
    @Transactional
    public Integer submitOrder(SubmitOrderDTO submitOrderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(submitOrderDTO, order);
        order.setOrderStatus(Order.ORDERSTATUS_NO);
        order.setOrderType(Order.ORDERTYPE_WEIXIN);
        order.setMemberId(memberMapper.selectByName(submitOrderDTO.getName()).getId());
        orderMapper.insert(order);
        return order.getId();
    }

    /**
     * 根据id查询预约信息
     *
     * @param id
     */
    @Override
    public FindOrderByIdVO findById(Integer id) {
        Order order = orderMapper.findById(id);
        FindOrderByIdVO findOrderByIdVO = new FindOrderByIdVO();
        BeanUtils.copyProperties(order, findOrderByIdVO);
        Setmeal setmeal = setmealMapper.selectById(order.getSetmealId());
        findOrderByIdVO.setSetmeal(setmeal.getCode());
        Member member = memberMapper.selectById(order.getMemberId());
        findOrderByIdVO.setMember(member.getName());
        ZoneId zoneId = ZoneId.systemDefault();
        findOrderByIdVO.setOrderDate(order.getOrderDate().toInstant().atZone(zoneId).toLocalDate());
        return findOrderByIdVO;
    }
}
