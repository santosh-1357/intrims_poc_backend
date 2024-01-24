package com.demo.domian.dto;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MonthChartData {

    private String name;

    private BigDecimal[] data  = {
            BigDecimal.valueOf(0),
            BigDecimal.valueOf(0),
            BigDecimal.valueOf(0),
            BigDecimal.valueOf(0),
            BigDecimal.valueOf(0),
            BigDecimal.valueOf(0),
            BigDecimal.valueOf(0),
            BigDecimal.valueOf(0),
            BigDecimal.valueOf(0),
            BigDecimal.valueOf(0),
            BigDecimal.valueOf(0),
            BigDecimal.valueOf(0)
    };

    public MonthChartData(String name) {
        this.name = name;
    }
}
