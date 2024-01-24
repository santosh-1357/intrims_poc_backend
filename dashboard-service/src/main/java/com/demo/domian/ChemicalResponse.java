package com.demo.domian;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChemicalResponse {

    private String importerName;
    private BigDecimal totalQuantity;
    private BigDecimal totalAmount;
}
