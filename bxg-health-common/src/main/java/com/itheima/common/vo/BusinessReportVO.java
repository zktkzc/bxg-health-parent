package com.itheima.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessReportVO {
    private Integer todayVisitsNumber;
    private LocalDate reportDate;
    private Integer todayNewMember;
    private Integer thisWeekVisitsNumber;
    private List<HotSetmeal> hotSetmeal;
    private Integer thisMonthNewMember;
    private Integer thisWeekNewMember;
    private Integer totalMember;
    private Integer thisMonthOrderNumber;
    private Integer thisMonthVisitsNumber;
    private Integer todayOrderNumber;
    private Integer thisWeekOrderNumber;
}
