package com.demo.service;

import com.demo.domian.PieChartResponse;
import com.demo.domian.dto.ChartDataDTO;
import com.demo.domian.dto.LineChartData;

import java.util.List;

public interface ChartDataService {

    LineChartData getLineChartData(ChartDataDTO dashboardDTO);
    LineChartData getBarChartData(ChartDataDTO dashboardDTO);

    List<PieChartResponse> getPieChartData(ChartDataDTO chartDataDTO);

}
