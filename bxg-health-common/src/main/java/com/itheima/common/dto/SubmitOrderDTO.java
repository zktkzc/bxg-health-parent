package com.itheima.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitOrderDTO {
    private String idCard;
    private String name;
    private Date orderDate;
    private Integer setmealId;
    private String sex;
    private String telephone;
    private String validateCode;
}
