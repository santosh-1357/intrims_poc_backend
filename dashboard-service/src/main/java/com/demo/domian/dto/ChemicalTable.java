package com.demo.domian.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ChemicalTable {

    private String casNumber;
    private String ecNumber;
    private String region;
    private String registrationNumber;
    private String initialTonnage;
    private String exportStatus;
    private String increaseTonnage;
    private String tonnageBand;
}
