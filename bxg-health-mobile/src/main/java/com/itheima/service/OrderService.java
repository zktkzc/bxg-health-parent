package com.itheima.service;

import com.itheima.common.dto.SubmitOrderDTO;
import com.itheima.common.vo.FindOrderByIdVO;

public interface OrderService {
    /**
     * 根据id查询预约信息
     */
    FindOrderByIdVO findById(Integer id);

    /**
     * 体检预约
     */
    Integer submitOrder(SubmitOrderDTO submitOrderDTO);
}
