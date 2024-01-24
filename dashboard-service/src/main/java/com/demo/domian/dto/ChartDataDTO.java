package com.demo.domian.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ChartDataDTO {

    private String tabName;
    private String exporterName;
    private String importerCountry;
    private String chemicalName;
    private int ritcCode;
    private boolean allData;
    private String fromDate;
    private String toDate;
}
