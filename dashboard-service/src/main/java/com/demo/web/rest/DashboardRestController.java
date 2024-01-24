package com.demo.web.rest;

import com.demo.domian.*;
import com.demo.domian.dto.*;
import com.demo.service.DashboardService;
import common.library.constants.app.FwUtils;
import common.library.pagination.options.GridOptions;
import common.library.pagination.response.GridSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.util.Objects;
/**
 * Controller for handling  Dashboard.
 *
 * @author santosh
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardRestController {

    private static Logger logger = LoggerFactory.getLogger(DashboardRestController.class);

    @Autowired
    DashboardService dashboardService;

    /**
     * POST request to get export_data
     *  @RequestBody dashboardDTO The input data for the dashboard
     * @return ApiResponse containing the export_data
     */
    @PostMapping(value = "/export_data" ,consumes = MediaType.APPLICATION_JSON)
    public ApiResponse<DashboardResponse> getExportData(
            @RequestBody DashboardDTO dashboardDTO){
        ApiResponse<DashboardResponse> apiResponse = new ApiResponse<>();
        try{
            DashboardResponse dashboardResponse = null;
            // Check if the input parameters are not null and not empty
            if(Objects.nonNull(dashboardDTO) && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())){
                // Log the payload information
                logger.info("/dashboard/export_data :: payload :"+dashboardDTO.toString());
                // Retrieve export data
                dashboardResponse = dashboardService.getExportData(dashboardDTO);
                apiResponse.setPayload(dashboardResponse);
            }
        }catch (Exception e){
            // Log and handle any exceptions that occur
            logger.error("/dashboard/export_data :: error :",e);
            e.printStackTrace();
        }
        // Return the ApiResponse containing the export_data
        return apiResponse;
    }

    /**
     * POST request to get export data of a specific country.
     * @RequestBody gridOptions The grid options containing metadata and search criteria
     * @return ApiResponse containing the GridSearchResponse of ExportsResponse
     */

    @PostMapping(value = "/country/export" ,consumes = MediaType.APPLICATION_JSON)
    public ApiResponse<GridSearchResponse<ExportsResponse>> getCountryExportsData(
            @RequestBody GridOptions<DashboardDTO> gridOptions){
        ApiResponse<GridSearchResponse<ExportsResponse>> apiResponse = new ApiResponse<>();
        try{
            GridSearchResponse<ExportsResponse> gridSearchResponse = null;
            // Check if the grid options are not null
            if(Objects.nonNull(gridOptions)){
                // Log the payload information
                logger.info("/dashboard/country/export :: payload :"+gridOptions.getMetadata().toString());
                // Retrieve country export data
                gridSearchResponse = dashboardService.getCountryExportsData(gridOptions);
                apiResponse.setPayload(gridSearchResponse);
            }
        }catch (Exception e){
            // Log and handle any exceptions that occur
            logger.error("/dashboard/country/export :: error :",e);
            e.printStackTrace();
        }
        // Return the ApiResponse containing the GridSearchResponse of ExportsResponse
        return apiResponse;
    }

    /**
     * POST request to get import data of a specific country.
     * @RequestBody gridOptions The grid options containing metadata and search criteria
     * @return ApiResponse containing the GridSearchResponse of ImportsResponse
     */
    @PostMapping(value = "/country/import" ,consumes = MediaType.APPLICATION_JSON)
    public ApiResponse<GridSearchResponse<ImportsResponse>> getCountryImportsData(
            @RequestBody GridOptions<DashboardDTO> gridOptions){
        ApiResponse<GridSearchResponse<ImportsResponse>> apiResponse = new ApiResponse<>();
        try{
            GridSearchResponse<ImportsResponse> gridSearchResponse = null;
            // Check if the grid options are not null
            if(Objects.nonNull(gridOptions)){
                // Log the payload information
                logger.info("/dashboard/country/import :: payload :"+gridOptions.getMetadata().toString());
                // Retrieve country import data
                gridSearchResponse = dashboardService.getCountryImportsData(gridOptions);
                apiResponse.setPayload(gridSearchResponse);
            }
        }catch (Exception e){
            // Log and handle any exceptions that occur
            logger.error("/dashboard/country/import :: error :",e);
            e.printStackTrace();
        }
        // Return the ApiResponse containing the GridSearchResponse of ImportsResponseDTO
        return apiResponse;
    }

    /**
     * POST request to get country-wise count (importer data).
     * @RequestBody dashboardDTO The input data for country-wise count
     * @return ApiResponse containing the CountryWiseCount
     */
    @PostMapping(value = "/country/count" ,consumes = MediaType.APPLICATION_JSON)
    public ApiResponse<CountryWiseCount> getCountryWiseCount(
            @RequestBody DashboardDTO dashboardDTO){
        ApiResponse<CountryWiseCount> apiResponse = new ApiResponse<>();
        try{
            CountryWiseCount countryWiseCount = null;
            // Check if the input parameters are not null and not empty
            if(Objects.nonNull(dashboardDTO)
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())){
                // Log the payload information
                logger.info("/dashboard/country/count :: payload :"+dashboardDTO.toString());
                // Retrieve country-wise count data
                countryWiseCount = dashboardService.getCountryWiseCount(dashboardDTO);
                apiResponse.setPayload(countryWiseCount);
            }
        }catch (Exception e){
            // Log and handle any exceptions that occur
            logger.error("/dashboard/country/count :: error :",e);
            e.printStackTrace();
        }
        // Return the ApiResponse containing the CountryWiseCount
        return apiResponse;
    }

    /**
     * POST request to get chemical data of a country.
     * @RequestBody gridOptions The grid options containing metadata and search criteria
     * @return ApiResponse containing the GridSearchResponse of ChemicalResponseDTO
     */
    @PostMapping(value = "/country/chemical" ,consumes = MediaType.APPLICATION_JSON)
    public ApiResponse<GridSearchResponse<ChemicalResponse>> getCountryChemicalData(
            @RequestBody GridOptions<DashboardDTO> gridOptions){
        ApiResponse<GridSearchResponse<ChemicalResponse>> apiResponse = new ApiResponse<>();
        try{
            GridSearchResponse<ChemicalResponse> gridSearchResponse = null;
            // Check if the grid options are not null
            if(Objects.nonNull(gridOptions)){
                // Log the payload information
                logger.info("/dashboard/country/chemical :: payload :"+gridOptions.getMetadata().toString());
                // Retrieve country chemical data
                gridSearchResponse = dashboardService.getCountryChemicalData(gridOptions);
                apiResponse.setPayload(gridSearchResponse);
            }
        }catch (Exception e){
            // Log and handle any exceptions that occur
            logger.error("/dashboard/country/chemical :: error :",e);
            e.printStackTrace();
        }
        // Return the ApiResponse containing the GridSearchResponse of ChemicalResponse
        return apiResponse;
    }

}
