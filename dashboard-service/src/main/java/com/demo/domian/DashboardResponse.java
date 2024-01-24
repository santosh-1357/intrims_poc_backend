package com.demo.domian;

import com.demo.domian.dto.CountryWiseCount;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DashboardResponse {

    private int chemicalCount;
    private int countryCount;
    private List<CountryWiseCount> countryList;
    private int buyersCount;

}
