package com.itheima.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotSetmeal {
    private Double proportion;
    private String name;
    private Integer setmeal_count;
}
