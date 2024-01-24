package com.demo.repository;

import com.demo.domian.PieChartResponse;
import com.demo.domian.dto.*;
import com.demo.util.constants.ChartConstants;
import com.demo.util.constants.query.ChartDataRepoQueryConstant;
import common.library.constants.app.FwUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ChartDataRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public LineChartData getLineChartData(ChartDataDTO chartDataDTO){
        LineChartData monthWiseExport = null;
        try {
            // Check if the input chartDataDTO and its required fields are not null or blank
            if(Objects.nonNull(chartDataDTO) && FwUtils.isNotBlankOrNullString(chartDataDTO.getTabName())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())){
                // Query to retrieve a list of MonthDTO objects based on the provided parameters
                List<MonthDTO> monthList = jdbcTemplate.query(ChartDataRepoQueryConstant.getLineChartDataQuery(chartDataDTO), new Object[] { },
                        new BeanPropertyRowMapper<MonthDTO>(MonthDTO.class));
                // Check if the monthList is not null and not empty
                if(FwUtils.isNotNullOrNotEmpty(monthList)){
                    monthWiseExport = new LineChartData();

                    // Create a MonthChartData object for quantity and populate its data array
                    MonthChartData quantityChart = new MonthChartData(ChartConstants.LABEL_QUANTITY);
                    BigDecimal[] quantityArray = quantityChart.getData();
                    monthList.forEach(
                            (month)->{
                                quantityArray[month.getIndex() - 1] = month.getTotalQuantity();
                            }
                    );

                    // Create a list of MonthChartData objects and add the quantity chart to it
                    List<MonthChartData> chartDataList = new ArrayList<>();
                    chartDataList.add(quantityChart);
                    // Set the chart data list in the LineChartData object
                    monthWiseExport.setChartData(chartDataList);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // Return the populated LineChartData object
        return monthWiseExport;
    }

    public LineChartData getTopFiveLineChartData(ChartDataDTO chartDataDTO){
        LineChartData lineChartData = null;
        try {
            // Check if the input chartDataDTO and its required fields are not null or blank
            if(Objects.nonNull(chartDataDTO) && FwUtils.isNotBlankOrNullString(chartDataDTO.getTabName())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())){
                // Query to retrieve a list of MonthDTO objects based on the provided parameters
                List<MonthDTO> monthList = jdbcTemplate.query(ChartDataRepoQueryConstant.getTopFiveLineChartDataQuery(chartDataDTO), new Object[] { },
                        new BeanPropertyRowMapper<MonthDTO>(MonthDTO.class));
                // Check if the monthList is not null and not empty
                if(FwUtils.isNotNullOrNotEmpty(monthList)){
                    List<MonthChartData> chartDataList = new ArrayList<>();
                    lineChartData = new LineChartData();
                    List<String> nameList = new ArrayList<>();
                    // Iterate through the monthList to identify unique entity names
                    for(MonthDTO monthDTO : monthList){
                        int index =nameList.indexOf(monthDTO.getEntityName());
                        if (index == -1 && FwUtils.isNotBlankOrNullString(monthDTO.getEntityName())){
                            nameList.add(monthDTO.getEntityName());
                        }
                    }

                    // Iterate through the unique entity names and create MonthChartData objects
                    for(String name : nameList){
                        MonthChartData quantityChart = new MonthChartData(name);
                        BigDecimal[] quantityArray = quantityChart.getData();
                        // Populate quantityArray based on the corresponding entity name
                        monthList.forEach(
                                (month)->{
                                    if(name.equalsIgnoreCase(month.getEntityName())){
                                        quantityArray[month.getIndex() - 1] = month.getTotalQuantity();
                                    }
                                }
                        );
                        quantityChart.setData(quantityArray);
                        chartDataList.add(quantityChart);
                    }
                    // Set the chart data list in the LineChartData object
                    lineChartData.setChartData(chartDataList);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // Return the populated LineChartData object
        return lineChartData;
    }

    /**
     * Returns a LineChartData object containing the bar chart data for the top five entities based on the provided parameters.
     *
     * @param chartDataDTO the ChartDataDTO object containing the input parameters
     * @return a LineChartData object containing the bar chart data
     */
    public LineChartData getBarChartData(ChartDataDTO chartDataDTO) {
        LineChartData monthWiseExport = null;
        try {
            // Check if the input chartDataDTO and its required fields are not null or blank
            if (Objects.nonNull(chartDataDTO) && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())) {
                // Query to retrieve a list of MonthDTO objects based on the provided parameters
                List<MonthDTO> monthList = jdbcTemplate.query(ChartDataRepoQueryConstant.getBarChartDataQuery(chartDataDTO),
                        new Object[] { }, new BeanPropertyRowMapper<>(MonthDTO.class));
                // Check if the monthList is not null and not empty
                if (FwUtils.isNotNullOrNotEmpty(monthList)) {
                    monthWiseExport = new LineChartData();
                    // Create a MonthChartData object for amount and populate its data array
                    MonthChartData amountChart = new MonthChartData(ChartConstants.LABEL_AMOUNT);
                    BigDecimal[] amountArray = amountChart.getData();
                    monthList.forEach(
                            (month) -> {
                                amountArray[month.getIndex() - 1] = month.getTotalAmount();
                            }
                    );
                    // Create a list of MonthChartData objects and add the amount chart to it
                    List<MonthChartData> chartDataList = new ArrayList<>();
                    chartDataList.add(amountChart);
                    // Set the chart data list in the LineChartData object
                    monthWiseExport.setChartData(chartDataList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Return the populated LineChartData object
        return monthWiseExport;
    }

    /**
     * Returns a LineChartData object containing the bar chart data for the top five entities based on the provided parameters.
     *
     * @param chartDataDTO the ChartDataDTO object containing the input parameters
     * @return a LineChartData object containing the bar chart data
     */
    public LineChartData getTopFiveBarChartData(ChartDataDTO chartDataDTO) {
        LineChartData lineChartData = null;
        try {
            // Check if the input chartDataDTO and its required fields are not null or blank
            if (Objects.nonNull(chartDataDTO) && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())) {
                // Query to retrieve a list of MonthDTO objects based on the provided parameters
                List<MonthDTO> monthList = jdbcTemplate.query(ChartDataRepoQueryConstant.getTopFiveBarChartDataQuery(chartDataDTO),
                        new Object[] { }, new BeanPropertyRowMapper<>(MonthDTO.class));
                // Check if the monthList is not null and not empty
                if (FwUtils.isNotNullOrNotEmpty(monthList)) {
                    // Create a new LineChartData object and a list to store MonthChartData objects
                    List<MonthChartData> chartDataList = new ArrayList<>();
                    lineChartData = new LineChartData();
                    List<String> nameList = new ArrayList<>();
                    // Iterate through the monthList to identify unique entity names
                    for (MonthDTO monthDTO : monthList) {
                        int index = nameList.indexOf(monthDTO.getEntityName());
                        if (index == -1 && FwUtils.isNotBlankOrNullString(monthDTO.getEntityName())) {
                            nameList.add(monthDTO.getEntityName());
                        }
                    }

                    // Iterate through the unique entity names and create MonthChartData objects
                    for (String name : nameList) {
                        MonthChartData amountChart = new MonthChartData(name);
                        BigDecimal[] amountArray = amountChart.getData();
                        // Populate amountArray based on the corresponding entity name
                        monthList.forEach(
                                (month) -> {
                                    if (name.equalsIgnoreCase(month.getEntityName())) {
                                        amountArray[month.getIndex() - 1] = month.getTotalAmount();
                                    }
                                }
                        );
                        amountChart.setData(amountArray);
                        chartDataList.add(amountChart);
                    }
                    // Set the chart data list in the LineChartData object
                    lineChartData.setChartData(chartDataList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Return the populated LineChartData object
        return lineChartData;
    }

    /**
     * Returns a list of PieChartResponse objects based on the provided parameters.
     *
     * @param chartDataDTO the ChartDataDTO object containing the input parameters
     * @return a list of PieChartResponse objects
     */
    public List<PieChartResponse> getPieChartData(ChartDataDTO chartDataDTO) {
        List<PieChartResponse> pieChartResponse = null;
        try {
            // Check if the input chartDataDTO and its required fields are not null or blank
            if (Objects.nonNull(chartDataDTO) && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())) {
                // Query to retrieve a list of PieChartResponse objects based on the provided parameters
                pieChartResponse = jdbcTemplate.query(ChartDataRepoQueryConstant.PIE_CHART_DATA_QUERY,
                        new Object[] {
                                chartDataDTO.getFromDate(), chartDataDTO.getToDate(), chartDataDTO.getExporterName()
                        },
                        new BeanPropertyRowMapper<>(PieChartResponse.class));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Return the populated LineChartData object
        return pieChartResponse;
    }

}
