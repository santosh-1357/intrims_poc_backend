package com.demo.domian;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExportsResponse {

    private String chemicalName;
    private int chemicalCode;
    private int totalImporter;
    private BigDecimal totalQuantity;
    private BigDecimal totalAmount;
}
