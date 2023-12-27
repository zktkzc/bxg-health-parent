package com.itheima.controller;

import com.itheima.common.constant.MessageConstant;
import com.itheima.common.entity.Result;
import com.itheima.service.SetmealService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Resource
    private SetmealService setmealService;

    /**
     * 获取所有套餐信息
     */
    @PostMapping("/getSetmeal")
    public Result getSetmeal() {
        return Result.success(MessageConstant.QUERY_SETMEAL_SUCCESS, setmealService.getSetmeal());
    }

    /**
     * 根据套餐id查询套餐详情
     */
    @PostMapping("/findById")
    public Result findById(Integer id) {
        return Result.success(MessageConstant.QUERY_SETMEAL_SUCCESS, setmealService.findById(id));
    }
}
