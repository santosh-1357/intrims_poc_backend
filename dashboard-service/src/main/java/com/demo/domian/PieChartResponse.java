package com.demo.domian;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PieChartResponse {

    private String chemicalName;
    private BigDecimal totalQuantity;
    private BigDecimal totalAmount;
}
