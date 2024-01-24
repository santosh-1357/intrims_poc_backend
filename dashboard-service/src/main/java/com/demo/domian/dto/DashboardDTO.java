package com.demo.domian.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DashboardDTO {

    private String exporterName;
    private String importerCountry;
    private String chemicalName;
    private int ritcCode;
    private String fromDate;
    private String toDate;
}
