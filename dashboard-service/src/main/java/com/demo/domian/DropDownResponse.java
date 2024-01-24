package com.demo.domian;

import com.demo.domian.dto.ChemicalDTO;
import com.demo.domian.dto.CountryWiseCount;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DropDownResponse {

    private List<CountryWiseCount> countryList;
    private List<ChemicalDTO> chemicalList;

}
