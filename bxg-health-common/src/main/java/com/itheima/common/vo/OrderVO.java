package com.itheima.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderVO {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String orderDate;
    private String orderType;
    private String orderStatus;
    private String fileNumber;
}
