package com.demo.service;

import com.demo.domian.ChemicalResponse;
import com.demo.domian.DashboardResponse;
import com.demo.domian.ExportsResponse;
import com.demo.domian.ImportsResponse;
import com.demo.domian.dto.*;
import common.library.pagination.options.GridOptions;
import common.library.pagination.response.GridSearchResponse;

public interface DashboardService {

    DashboardResponse getExportData(DashboardDTO dashboardDTO);

    GridSearchResponse<ExportsResponse> getCountryExportsData(GridOptions<DashboardDTO> gridOptions);

    GridSearchResponse<ImportsResponse> getCountryImportsData(GridOptions<DashboardDTO> gridOptions);

    CountryWiseCount getCountryWiseCount(DashboardDTO dashboardDTO);

    GridSearchResponse<ChemicalResponse> getCountryChemicalData(GridOptions<DashboardDTO> gridOptions);

}
