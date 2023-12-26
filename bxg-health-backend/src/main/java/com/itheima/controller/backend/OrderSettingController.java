package com.itheima.controller.backend;

import com.itheima.common.constant.MessageConstant;
import com.itheima.common.entity.Result;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Resource
    private OrderSettingService orderSettingService;

    /**
     * 下载模版
     */
    @GetMapping("/download")
    public String download(String filename, HttpServletResponse response) {
        orderSettingService.download(filename, response);
        return "文件下载";
    }

    /**
     * 根据日期修改可预约人数
     */
    @PostMapping("/editNumberByOrderDate")
    public Result editNumberByOrderDate(@RequestBody OrderSetting orderSetting) {
        orderSettingService.editNumberByOrderDate(orderSetting);
        return Result.success(MessageConstant.ORDERSETTING_SUCCESS);
    }

    /**
     * 根据年月查询对应的预约设置信息
     */
    @GetMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String month) {
        return Result.success(MessageConstant.GET_ORDERSETTING_SUCCESS, orderSettingService.getOrderSettingByMonth(month));
    }

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public Result upload(@RequestBody MultipartFile excelFile) {
        orderSettingService.upload(excelFile);
        return Result.success(MessageConstant.ORDERSETTING_SUCCESS);
    }
}
