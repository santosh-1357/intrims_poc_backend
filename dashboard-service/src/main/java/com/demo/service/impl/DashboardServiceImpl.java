package com.demo.service.impl;

import com.demo.domian.dto.*;
import com.demo.domian.*;
import com.demo.filter.impl.MetadataFilterImpl;
import com.demo.repository.DashboardRepository;
import com.demo.service.DashboardService;
import common.library.constants.app.FwUtils;
import common.library.pagination.options.GridOptions;
import common.library.pagination.response.GridSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DashboardServiceImpl implements DashboardService {
    
    @Autowired
    DashboardRepository dashboardRepository;

    @Autowired
    MetadataFilterImpl metadataFilter;
    @Override
    public DashboardResponse getExportData(DashboardDTO dashboardDTO) {
        DashboardResponse dashboardResponse = null;
        try{
            //cheack wheather object is null or not
            if(Objects.nonNull(dashboardDTO) && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())){
             //get export data from database
                dashboardResponse = dashboardRepository.getExportData(dashboardDTO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dashboardResponse;
    }

    @Override
    public GridSearchResponse<ExportsResponse> getCountryExportsData(GridOptions<DashboardDTO> gridOptions) {
        GridSearchResponse<ExportsResponse> gridSearchResponse = null;
        try {
            //cheack wheather object is null or not
            if(Objects.nonNull(gridOptions)){
                // get countrywise export data from database
                gridSearchResponse = dashboardRepository.getCountryExportsData(metadataFilter.filterMetadata(gridOptions));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return gridSearchResponse;
    }

    @Override
    public GridSearchResponse<ImportsResponse> getCountryImportsData(GridOptions<DashboardDTO> gridOptions) {
        GridSearchResponse<ImportsResponse> gridSearchResponse = null;
        try {
            //cheack wheather object is null or not
            if(Objects.nonNull(gridOptions)){
                //get countrywise import data from database
                gridSearchResponse = dashboardRepository.getCountryImportsData(metadataFilter.filterMetadata(gridOptions));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return gridSearchResponse;
    }

    @Override
    public CountryWiseCount getCountryWiseCount(DashboardDTO dashboardDTO) {
        CountryWiseCount countryWiseCount = null;
        try{
            //cheack wheather object is null or not
            if(Objects.nonNull(dashboardDTO)
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())){
                // get countrywise count from database
                countryWiseCount = dashboardRepository.getCountryWiseCount(dashboardDTO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return countryWiseCount;
    }

    @Override
    public GridSearchResponse<ChemicalResponse> getCountryChemicalData(GridOptions<DashboardDTO> gridOptions) {
        GridSearchResponse<ChemicalResponse> gridSearchResponse = null;
        try {
            //cheack wheather object is null or not
            if(Objects.nonNull(gridOptions)){
                // get countrywise chemical data from database
                gridSearchResponse = dashboardRepository.getCountryChemicalData(metadataFilter.filterMetadata(gridOptions));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return gridSearchResponse;
    }
}
