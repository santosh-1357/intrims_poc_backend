package com.demo.service.impl;

import com.demo.domian.PieChartResponse;
import com.demo.domian.dto.ChartDataDTO;
import com.demo.domian.dto.LineChartData;
import com.demo.repository.ChartDataRepository;
import com.demo.service.ChartDataService;
import common.library.constants.app.FwUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChartDataServiceImpl implements ChartDataService {

    @Autowired
    ChartDataRepository chartDataRepository;
    @Override
    public LineChartData getLineChartData(ChartDataDTO chartDataDTO) {
        LineChartData monthWiseExport = null;
        try{
            //cheack wheather object is null or not
            if(Objects.nonNull(chartDataDTO)
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())){
                if(chartDataDTO.isAllData()){
                    //get line chart data from database
                    monthWiseExport = chartDataRepository.getLineChartData(chartDataDTO);
                } else {
                    //get top five line chart data from database
                    monthWiseExport = chartDataRepository.getTopFiveLineChartData(chartDataDTO);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return monthWiseExport;
    }

    @Override
    public LineChartData getBarChartData(ChartDataDTO chartDataDTO) {
        LineChartData monthWiseExport = null;
        try{
            //cheack wheather object is null or not
            if(Objects.nonNull(chartDataDTO)
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())){
                if(chartDataDTO.isAllData()){
                    //get Bar chart data from database
                    monthWiseExport = chartDataRepository.getBarChartData(chartDataDTO);
                } else {
                    //get top five bar chart data from database
                    monthWiseExport = chartDataRepository.getTopFiveBarChartData(chartDataDTO);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return monthWiseExport;
    }

    /**
     * Returns a list of PieChartResponse objects based on the given ChartDataDTO object.
     *
     * @param chartDataDTO the ChartDataDTO object containing the necessary parameters to retrieve the PieChartResponse data
     * @return a list of PieChartResponse objects, or null if an error occurs
     */
    @Override
    public List<PieChartResponse> getPieChartData(ChartDataDTO chartDataDTO) {
        List<PieChartResponse> pieChartResponses = null;
        try {
            // Check whether the given ChartDataDTO object is not null, and that the exporter name, from date, and to date are not empty
            if (Objects.nonNull(chartDataDTO) && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())) {
                // Retrieve the PieChartResponse data from the database
                pieChartResponses = chartDataRepository.getPieChartData(chartDataDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pieChartResponses;
    }

}
