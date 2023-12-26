package com.itheima.service.impl;

import com.itheima.common.vo.OrderSettingVO;
import com.itheima.dao.OrderSettingMapper;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderSettingServiceImpl implements OrderSettingService {
    @Resource
    private OrderSettingMapper orderSettingMapper;

    /**
     * 文件上传
     *
     * @param excelFile
     */
    @Override
    public void upload(MultipartFile excelFile) {
        try {
            InputStream in = excelFile.getInputStream();
            XSSFWorkbook excel = new XSSFWorkbook(in);
            XSSFSheet sheet = excel.getSheet("预约设置模板");
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Date dateCellValue = sheet.getRow(i).getCell(0).getDateCellValue();
                ZoneId zoneId = ZoneId.systemDefault();
                LocalDate orderDate = dateCellValue.toInstant().atZone(zoneId).toLocalDate();
                String number = String.valueOf(sheet.getRow(i).getCell(1).getNumericCellValue());
                OrderSetting orderSetting = new OrderSetting();
                orderSetting.setOrderDate(orderDate);
                orderSetting.setNumber((int) Double.parseDouble(number));
                if (orderSettingMapper.selectByDate(orderDate) == null) {
                    orderSettingMapper.insert(orderSetting);
                } else {
                    orderSettingMapper.updateNumberByOrderDate(orderSetting);
                }
            }
        } catch (Exception e) {
            log.error("文件上传失败: {}", e.getMessage());
        }
    }

    /**
     * 根据年月查询对应的预约设置信息
     *
     * @param month
     * @return
     */
    @Override
    public List<OrderSettingVO> getOrderSettingByMonth(String month) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] split = month.split("-");
        month = dateTimeFormatter.format(LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), 1));
        month = month.substring(0, month.lastIndexOf("-"));
        List<OrderSetting> orderSettings = orderSettingMapper.selectByMonth(month);
        List<OrderSettingVO> orderSettingVOList = new ArrayList<>();
        orderSettings.forEach(orderSetting -> {
            OrderSettingVO orderSettingVO = OrderSettingVO.builder()
                    .date(orderSetting.getOrderDate().getDayOfMonth() + "")
                    .number(orderSetting.getNumber())
                    .reservations(orderSetting.getReservations())
                    .build();
            orderSettingVOList.add(orderSettingVO);
        });
        return orderSettingVOList;
    }

    /**
     * 根据日期修改可预约人数
     *
     * @param orderSetting
     */
    @Override
    public void editNumberByOrderDate(OrderSetting orderSetting) {
        try {
            if (orderSettingMapper.selectByDate(orderSetting.getOrderDate()) == null) {
                orderSettingMapper.insert(orderSetting);
            } else {
                orderSettingMapper.updateNumberByOrderDate(orderSetting);
            }
        } catch (Exception e) {
            log.error("修改失败：{}", e.getMessage());
        }
    }

    @Override
    public void download(String filename, HttpServletResponse response) {
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("templates/" + filename);
            ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-disposition", "attachment;filename=" + filename);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            log.error("下载文件失败: {}", e.getMessage());
        }
    }
}
