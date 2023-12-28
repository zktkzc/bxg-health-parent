package com.itheima.service.impl;

import com.itheima.common.vo.*;
import com.itheima.dao.MemberMapper;
import com.itheima.dao.OrderMapper;
import com.itheima.dao.SetmealMapper;
import com.itheima.pojo.Order;
import com.itheima.pojo.SetmealReport;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private SetmealMapper setmealMapper;

    /**
     * 导出为Excel
     *
     * @param response
     */
    @Override
    public void exportBusinessReport(HttpServletResponse response) {
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("templates/report_template.xlsx");
            XSSFWorkbook excel = new XSSFWorkbook(in);
            XSSFSheet sheet = excel.getSheet("Sheet1");
            BusinessReportVO businessReportData = getBusinessReportData();
            sheet.getRow(2).getCell(5).setCellValue(businessReportData.getReportDate().toString());
            sheet.getRow(4).getCell(5).setCellValue(businessReportData.getTodayNewMember());
            sheet.getRow(4).getCell(7).setCellValue(businessReportData.getTotalMember());
            sheet.getRow(5).getCell(5).setCellValue(businessReportData.getThisWeekNewMember());
            sheet.getRow(5).getCell(7).setCellValue(businessReportData.getThisMonthNewMember());
            sheet.getRow(7).getCell(5).setCellValue(businessReportData.getTodayOrderNumber());
            sheet.getRow(7).getCell(7).setCellValue(businessReportData.getTodayVisitsNumber());
            sheet.getRow(8).getCell(5).setCellValue(businessReportData.getThisWeekOrderNumber());
            sheet.getRow(8).getCell(7).setCellValue(businessReportData.getThisWeekVisitsNumber());
            sheet.getRow(9).getCell(5).setCellValue(businessReportData.getThisMonthOrderNumber());
            sheet.getRow(9).getCell(7).setCellValue(businessReportData.getThisMonthVisitsNumber());
            List<HotSetmeal> hotSetmeal = businessReportData.getHotSetmeal();
            for (int i = 0; i < 4; i++) {
                sheet.getRow(12 + i).getCell(4).setCellValue(hotSetmeal.get(i).getName());
                sheet.getRow(12 + i).getCell(5).setCellValue(hotSetmeal.get(i).getSetmeal_count());
                sheet.getRow(12 + i).getCell(6).setCellValue(hotSetmeal.get(i).getProportion());
            }
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename=report.xlsx");
            excel.write(response.getOutputStream());
            excel.close();
            in.close();
        } catch (Exception e) {
            log.error("导出营业数据Excel失败：{}", e.getMessage());
        }
    }

    /**
     * 获取运营统计数据
     */
    @Override
    public BusinessReportVO getBusinessReportData() {
        LocalDate today = LocalDate.now();
        Integer todayVisitsNumber = orderMapper.countByRegDateAndOrderStatus(today, Order.ORDERSTATUS_YES);
        LocalDate reportDate = today;
        Integer todayNewMember = memberMapper.countByDate(today);
        Integer todayOrderNumber = orderMapper.countByRegDateAndOrderStatus(today, Order.ORDERSTATUS_NO);
        Integer thisWeekVisitsNumber = 0;
        Integer thisMonthNewMember = 0;
        Integer thisWeekNewMember = 0;
        Integer thisWeekOrderNumber = 0;
        Integer totalMember = memberMapper.selectAll();
        Integer thisMonthOrderNumber = 0;
        Integer thisMonthVisitsNumber = 0;
        List<HotSetmeal> hotSetmeal = new ArrayList<>();
        List<SetmealReport> setmealReports = orderMapper.countSetmeal();
        for (SetmealReport setmealReport : setmealReports) {
            String setmealName = setmealMapper.findNameById(setmealReport.getSetmealId());
            double v = setmealReport.getValue() / orderMapper.countSetmealTotal();
            String s = String.valueOf(v);
            double proportion = Double.parseDouble(s.substring(0, s.indexOf(".") + 4));
            HotSetmeal hotSetmeal1 = HotSetmeal.builder()
                    .name(setmealName)
                    .setmeal_count(setmealReport.getValue())
                    .proportion(proportion)
                    .build();
            hotSetmeal.add(hotSetmeal1);
        }
        try {
            String month = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).substring(0, 7);
            thisMonthNewMember = memberMapper.countNewMemberByMonth(month);
            thisMonthOrderNumber = orderMapper.countByRegDateAndOrderStatusOfMonth(month, Order.ORDERSTATUS_NO);
            thisMonthVisitsNumber = orderMapper.countByRegDateAndOrderStatusOfMonth(month, Order.ORDERSTATUS_YES);
            LocalDate begin = today.with(DayOfWeek.MONDAY);
            LocalDate end = begin.plusDays(6);
            thisWeekNewMember = memberMapper.countMemberByWeek(begin, end);
            thisWeekOrderNumber = orderMapper.countByRegDateAndOrderStatusOfWeek(begin, end, Order.ORDERSTATUS_NO);
            thisWeekVisitsNumber = orderMapper.countByRegDateAndOrderStatusOfWeek(begin, end, Order.ORDERSTATUS_YES);
        } catch (Exception e) {
            log.error("获取运营统计数据失败：{}", e.getMessage());
        }
        return BusinessReportVO.builder()
                .todayVisitsNumber(todayVisitsNumber)
                .reportDate(reportDate)
                .todayNewMember(todayNewMember)
                .thisWeekVisitsNumber(thisWeekVisitsNumber)
                .hotSetmeal(hotSetmeal)
                .thisMonthNewMember(thisMonthNewMember)
                .thisWeekNewMember(thisWeekNewMember)
                .totalMember(totalMember)
                .thisMonthOrderNumber(thisMonthOrderNumber)
                .thisMonthVisitsNumber(thisMonthVisitsNumber)
                .todayOrderNumber(todayOrderNumber)
                .thisWeekOrderNumber(thisWeekOrderNumber)
                .build();
    }

    /**
     * 获取套餐预约占比
     */
    @Override
    public SetmealReportVO getSetmealReport() {
        List<SetmealReport> setmealReports = orderMapper.countSetmeal();
        List<String> setmealNames = new ArrayList<>();
        List<SetmealCountVO> setmealCount = new ArrayList<>();
        for (SetmealReport setmealReport : setmealReports) {
            String setmealName = setmealMapper.findNameById(setmealReport.getSetmealId());
            setmealNames.add(setmealName);
            SetmealCountVO setmealCountVO = SetmealCountVO.builder()
                    .name(setmealName)
                    .value(setmealReport.getValue())
                    .build();
            setmealCount.add(setmealCountVO);
        }
        return SetmealReportVO.builder()
                .setmealNames(setmealNames)
                .setmealCount(setmealCount)
                .build();
    }

    /**
     * 获取会员数据统计数量
     */
    @Override
    public MemberReportVO getMemberReport() {
        List<String> months = null;
        List<Integer> memberCount = null;
        try {
            LocalDate now = LocalDate.now();
            months = new ArrayList<>();
            memberCount = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                LocalDate beginDate = now.minusMonths(12 - i);
                beginDate = LocalDate.of(beginDate.getYear(), beginDate.getMonth(), beginDate.lengthOfMonth());
                String date = beginDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                months.add(date);
                memberCount.add(memberMapper.countMemberByMonth(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            }
        } catch (Exception e) {
            log.error("获取会员统计数量失败：{}", e.getMessage());
        }
        return MemberReportVO.builder()
                .months(months)
                .memberCount(memberCount)
                .build();
    }
}
