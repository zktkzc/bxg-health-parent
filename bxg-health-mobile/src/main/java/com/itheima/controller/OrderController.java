package com.itheima.controller;

import com.itheima.common.constant.MessageConstant;
import com.itheima.common.dto.SubmitOrderDTO;
import com.itheima.common.entity.Result;
import com.itheima.service.OrderService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 根据预约id查询信息
     */
    @PostMapping("/findById")
    public Result findById(Integer id) {
        return Result.success(MessageConstant.QUERY_ORDER_SUCCESS, orderService.findById(id));
    }

    /**
     * 体检预约
     */
    @PostMapping("/submitOrder")
    public Result submitOrder(@RequestBody SubmitOrderDTO submitOrderDTO) {
        String code = (String) redisTemplate.opsForValue().get("ValidateCode4Order_" + submitOrderDTO.getTelephone());
        if (code == null) {
            return Result.fail("验证码已过期");
        } else if (!code.equals(submitOrderDTO.getValidateCode())) {
            return Result.fail(MessageConstant.VALIDATECODE_ERROR);
        }
        return Result.success(MessageConstant.ORDER_SUCCESS, orderService.submitOrder(submitOrderDTO));
    }
}
