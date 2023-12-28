package com.itheima.service;

import com.itheima.common.vo.BusinessReportVO;
import com.itheima.common.vo.MemberReportVO;
import com.itheima.common.vo.SetmealReportVO;

import javax.servlet.http.HttpServletResponse;

public interface ReportService {
    /**
     * 获取会员数据统计数量
     */
    MemberReportVO getMemberReport();

    /**
     * 获取运营统计数据
     */
    BusinessReportVO getBusinessReportData();

    /**
     * 获取套餐预约占比
     */
    SetmealReportVO getSetmealReport();

    /**
     * 导出为Excel
     *
     * @param response
     */
    void exportBusinessReport(HttpServletResponse response);
}
