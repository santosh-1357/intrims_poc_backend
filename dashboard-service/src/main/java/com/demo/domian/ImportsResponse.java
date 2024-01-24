package com.demo.domian;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ImportsResponse {

    private int importerId;
    private String importerName;
    private int chemicalCount;
    private BigDecimal totalQuantity;
    private BigDecimal totalAmount;
}
