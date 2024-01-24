package com.demo.domian.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ImporterTable {

    private String importerName;
    private String importerCountry;
    private String dateOfTransaction;
    private String exportReference;
}
