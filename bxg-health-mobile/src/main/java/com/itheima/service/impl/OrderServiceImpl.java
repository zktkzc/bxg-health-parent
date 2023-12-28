package com.itheima.service.impl;

import com.itheima.common.dto.SubmitOrderDTO;
import com.itheima.common.vo.FindOrderByIdVO;
import com.itheima.mapper.MemberMapper;
import com.itheima.mapper.OrderMapper;
import com.itheima.mapper.OrderSettingMapper;
import com.itheima.mapper.SetmealMapper;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;
import com.itheima.pojo.Setmeal;
import com.itheima.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private SetmealMapper setmealMapper;
    @Resource
    private OrderSettingMapper orderSettingMapper;

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
        Member member = memberMapper.selectByPhone(submitOrderDTO.getTelephone());
        order.setMemberId(member.getId());
        orderMapper.insert(order);
        OrderSetting orderSetting = orderSettingMapper.selectByOrderDate(submitOrderDTO.getOrderDate());
        if (orderSetting == null) {
            orderSetting = new OrderSetting();
            orderSetting.setOrderDate(submitOrderDTO.getOrderDate());
            orderSetting.setNumber(0);
            orderSetting.setReservations(1);
            orderSettingMapper.insert(orderSetting);
        } else {
            orderSetting.setOrderDate(submitOrderDTO.getOrderDate());
            orderSetting.setReservations(orderSetting.getReservations() + 1);
            orderSettingMapper.update(orderSetting);
        }
        String fileNumber = DateTimeFormatter.ofPattern("yyyyMMdd").format(submitOrderDTO.getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) + member.getId();
        member.setFileNumber(fileNumber);
        member.setIdCard(submitOrderDTO.getIdCard());
        member.setName(submitOrderDTO.getName());
        member.setSex(submitOrderDTO.getSex());
        TemporalAccessor instant = DateTimeFormatter.ofPattern("yyyyMMdd").parse(submitOrderDTO.getIdCard().substring(6, 14));
        Date birthday = Date.from(java.time.LocalDate.from(instant).atStartOfDay(ZoneId.systemDefault()).toInstant());
        member.setBirthday(birthday);
        memberMapper.update(member);
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
