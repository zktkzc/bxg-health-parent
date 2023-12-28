package com.itheima.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetmealReportVO {
    private List<String> setmealNames;
    private List<SetmealCountVO> setmealCount;
}
