package com.itheima.controller.backend;

import com.itheima.common.constant.MessageConstant;
import com.itheima.common.entity.Result;
import com.itheima.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Resource
    private ReportService reportService;

    /**
     * 获取会员数据统计数量
     */
    @GetMapping("/getMemberReport")
    public Result getMemberReport() {
        return Result.success(MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS, reportService.getMemberReport());
    }

    /**
     * 获取运营统计数据
     */
    @GetMapping("/getBusinessReportData")
    public Result getBusinessReportData() {
        return Result.success(MessageConstant.GET_BUSINESS_REPORT_SUCCESS, reportService.getBusinessReportData());
    }

    /**
     * 获取套餐预约占比
     */
    @GetMapping("/getSetmealReport")
    public Result getSetmealReport() {
        return Result.success(MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS, reportService.getSetmealReport());
    }

    /**
     * 导出为Excel
     */
    @GetMapping("/exportBusinessReport")
    public String exportBusinessReport(HttpServletResponse response) {
        reportService.exportBusinessReport(response);
        return "下载文件";
    }
}
