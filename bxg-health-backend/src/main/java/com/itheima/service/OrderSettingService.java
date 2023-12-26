package com.itheima.service;

import com.itheima.common.vo.OrderSettingVO;
import com.itheima.pojo.OrderSetting;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface OrderSettingService {
    /**
     * 下载模版
     */
    void download(String filename, HttpServletResponse response);

    /**
     * 根据日期修改可预约人数
     */
    void editNumberByOrderDate(OrderSetting orderSetting);

    /**
     * 根据年月查询对应的预约设置信息
     */
    List<OrderSettingVO> getOrderSettingByMonth(String month);

    /**
     * 文件上传
     */
    void upload(MultipartFile excelFile);
}
