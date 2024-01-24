package com.demo.web.rest;

import com.demo.domian.ApiResponse;
import com.demo.domian.PieChartResponse;
import com.demo.domian.dto.ChartDataDTO;
import com.demo.domian.dto.LineChartData;
import com.demo.service.ChartDataService;
import common.library.constants.app.FwUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;
/**
 * Controller for handling  Chart Data.
 *
 * @author santosh
 */
@RestController
@RequestMapping("/data")
public class ChartDataRestController {

    private static final Logger logger = LoggerFactory.getLogger(ChartDataRestController.class);

    @Autowired
    ChartDataService chartDataService;

    /**
     * Controller method for handling POST requests to create a line chart.
     * @RequestBody chartDataDTO The data transfer object containing chart parameters.
     * @return An ApiResponse with a success message if the line chart data is fetched, or an error message otherwise.
     */
    @PostMapping(value = "/line-chart" ,consumes = MediaType.APPLICATION_JSON)
    public ApiResponse<LineChartData> getChemicalLineChartData(
            @RequestBody ChartDataDTO chartDataDTO){
        ApiResponse<LineChartData> apiResponse = new ApiResponse<>();
        try{
            LineChartData monthWiseExport = null;
            // Check if the incoming chartDataDTO is not null and contains required parameters
            if(Objects.nonNull(chartDataDTO)
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())){
                logger.info("/data/line-chart :: payload :"+chartDataDTO);
                // Fetch line chart data using the provided parameters
                monthWiseExport = chartDataService.getLineChartData(chartDataDTO);
                // Set the fetched data in the ApiResponse payload
                apiResponse.setPayload(monthWiseExport);
            }
        }catch (Exception e){
            // Log and handle any exceptions that may occur during the process
            logger.error("/data/line-chart :: error :",e);
            e.printStackTrace();
        }
        // Return the ApiResponse
        return apiResponse;
    }

    /**
     * Controller method for handling POST requests to create a bar chart.
     * @RequestBody chartDataDTO The data transfer object containing chart parameters.
     * @return An ApiResponse with a success message if the bar chart data is fetched, or an error message otherwise.
     */
    @PostMapping(value = "/bar-chart" ,consumes = MediaType.APPLICATION_JSON)
    public ApiResponse<LineChartData> getChemicalBarChartData(
            @RequestBody ChartDataDTO chartDataDTO){
        ApiResponse<LineChartData> apiResponse = new ApiResponse<>();
        try{
            LineChartData monthWiseExport = null;
            // Check if the incoming chartDataDTO is not null and contains required parameters
            if(Objects.nonNull(chartDataDTO)
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())){
                logger.info("/data/bar-chart :: payload :"+chartDataDTO);
                // Fetch bar chart data using the provided parameters
                monthWiseExport = chartDataService.getBarChartData(chartDataDTO);
                // Set the fetched data in the ApiResponse payload
                apiResponse.setPayload(monthWiseExport);
            }
        }catch (Exception e){
            logger.error("/data/line-chart :: error :",e);
            e.printStackTrace();
        }
        // Return the ApiResponse to the client
        return apiResponse;
    }

    /**
     * Controller method for handling POST requests to create a pie chart.
     *
     * @param chartDataDTO The data transfer object containing chart parameters.
     * @return An ApiResponse with a success message if the pie chart data is fetched, or an error message otherwise.
     */
    @PostMapping(value = "/pie-chart", consumes = MediaType.APPLICATION_JSON)
    public ApiResponse<List<PieChartResponse>> getPieChartData(
            @RequestBody ChartDataDTO chartDataDTO) {
        ApiResponse<List<PieChartResponse>> apiResponse = new ApiResponse<>();
        try {
            List<PieChartResponse> pieChartResponse = null;
            // Check if the incoming chartDataDTO is not null and contains required parameters
            if (Objects.nonNull(chartDataDTO)
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())) {
                logger.info("/data/pie-chart :: payload : " + chartDataDTO);
                // Fetch pie chart data using the provided parameters
                pieChartResponse = chartDataService.getPieChartData(chartDataDTO);
                // Set the fetched data in the ApiResponse payload
                apiResponse.setPayload(pieChartResponse);
            }
        } catch (Exception e) {
            logger.error("/data/pie-chart :: error :", e);
            e.printStackTrace();
        }
        // Return the ApiResponse to the client
        return apiResponse;
    }

}
