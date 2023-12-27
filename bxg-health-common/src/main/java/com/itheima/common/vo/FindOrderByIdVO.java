package com.itheima.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindOrderByIdVO {
    private String orderType;
    private String member;
    private LocalDate orderDate;
    private String setmeal;
}
