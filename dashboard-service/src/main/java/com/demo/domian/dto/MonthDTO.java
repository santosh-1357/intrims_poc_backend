package com.demo.domian.dto;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MonthDTO {

    private int index;
    private String monthName;
    private String entityName;
    private BigDecimal totalQuantity;
    private BigDecimal totalAmount;
}
