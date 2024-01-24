package com.demo.domian.dto;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CountryWiseCount {

    private long id;
    private String countryName;
    private int chemicalCount;
    private int importerCount;
    private BigDecimal totalQuantity;
    private BigDecimal totalAmount;
}
