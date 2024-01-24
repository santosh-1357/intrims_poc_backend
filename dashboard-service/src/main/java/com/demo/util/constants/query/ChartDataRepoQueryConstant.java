package com.demo.util.constants.query;

import com.demo.domian.dto.ChartDataDTO;
import com.demo.util.constants.Constants;
import common.library.constants.app.FwUtils;

import java.util.Objects;

public interface ChartDataRepoQueryConstant {

    String CHEMICAL_EXPORT_QUANTITY_LINE_CHART = " SELECT EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
            " TO_CHAR(t.bill_date, 'Month') AS monthName, ROUND((SUM(t.quantity_in_tons)),2) AS totalQuantity, \n" +
            " ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
            " FROM trade t \n" +
            " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
            " WHERE \n" +
            " t.bill_date BETWEEN to_date( ? ,'YYYY-MM') AND to_date( ? ,'YYYY-MM') \n" +
            " AND t.quantity_in_tons IS NOT NULL AND t.uqc IN('LTR','GMS','PCS','NOS','MTS','KGS') \n" +
            " AND e.name = ? AND t.country = ? \n" +
            " GROUP BY TO_CHAR(t.bill_date, 'Month') " +
            " ORDER BY index ";

    String CHEMICAL_EXPORT_AMOUNT_BAR_CHART = " SELECT EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
            " TO_CHAR(t.bill_date, 'Month') AS monthName, \n" +
            " ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
            " FROM trade t INNER JOIN exporter e ON t.exporter_id = e.id \n" +
            " INNER JOIN importer i ON t.importer_id = i.id \n" +
            " INNER JOIN chemical c ON t.ritc_code = t.ritc_code \n" +
            " WHERE \n" +
            " t.bill_date BETWEEN to_date( ? ,'YYYY-MM') AND to_date( ? ,'YYYY-MM') \n" +
            " AND t.quantity_in_tons IS NOT NULL AND t.uqc IN('LTR','GMS','PCS','NOS','MTS','KGS') \n" +
            " AND e.name = ? AND t.country = ? \n" +
            " GROUP BY TO_CHAR(t.bill_date, 'Month') \n" +
            " ORDER BY index ";

    String IMPORTER_QUANTITY_LINE_CHART = " SELECT EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
            " TO_CHAR(t.bill_date, 'Month') AS monthName, ROUND((SUM(t.quantity_in_tons)),2) AS totalQuantity, \n" +
            " ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
            " FROM trade t \n" +
            " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
            " WHERE \n" +
            " t.bill_date BETWEEN to_date( ? ,'YYYY-MM') AND to_date( ? ,'YYYY-MM') \n" +
            " AND t.quantity_in_tons IS NOT NULL AND t.uqc IN('LTR','GMS','PCS','NOS','MTS','KGS') \n" +
            " AND e.name = ? AND t.country = ? \n" +
            " GROUP BY TO_CHAR(t.bill_date, 'Month') " +
            " ORDER BY index ";

    String IMPORTER_AMOUNT_BAR_CHART = " SELECT EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
            " TO_CHAR(t.bill_date, 'Month') AS monthName, \n" +
            " ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
            " FROM trade t INNER JOIN exporter e ON t.exporter_id = e.id \n" +
            " INNER JOIN importer i ON t.importer_id = i.id \n" +
            " INNER JOIN chemical c ON t.ritc_code = t.ritc_code \n" +
            " WHERE \n" +
            " t.bill_date BETWEEN to_date( ? ,'YYYY-MM') AND to_date( ? ,'YYYY-MM') \n" +
            " AND t.quantity_in_tons IS NOT NULL AND t.uqc IN('LTR','GMS','PCS','NOS','MTS','KGS') \n" +
            " AND e.name = ? AND t.country = ? \n" +
            " GROUP BY TO_CHAR(t.bill_date, 'Month') \n" +
            " ORDER BY index ";

    String PIE_CHART_DATA_QUERY = " SELECT DISTINCT TRIM(c.name) AS chemicalName, ROUND((SUM(t.quantity_in_tons)),2) AS totalQuantity , \n" +
            " ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
            " FROM trade t \n" +
            " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
            " INNER JOIN importer i ON t.importer_id = i.id \n" +
            " INNER JOIN chemical c ON t.ritc_code = c.ritc_code \n" +
            " WHERE 1=1  AND t.quantity_in_tons IS NOT NULL \n" +
            " AND t.uqc IN('LTR','GMS','PCS','NOS','MTS','KGS')  \n" +
            " AND t.bill_date BETWEEN to_date( ? ,'YYYY-MM') AND to_date( ? ,'YYYY-MM') \n" +
            " AND e.name = ? \n" +
            " GROUP BY chemicalName ";

    // LINE CHART DATA QUERY

    public static String getLineChartDataQuery(ChartDataDTO chartDataDTO){
        String LINE_CHART_DATA_QUERY = "";
        try{
            if(Objects.nonNull(chartDataDTO) && FwUtils.isNotBlankOrNullString(chartDataDTO.getTabName())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())){
                if(chartDataDTO.getTabName().equalsIgnoreCase(Constants.EXPORT_TAB)){
                    // CHEMICAL EXPORT LINE CHART QUERY
                    LINE_CHART_DATA_QUERY = " SELECT EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
                            " TO_CHAR(t.bill_date, 'Month') AS monthName, ROUND((SUM(t.quantity_in_tons)),2) AS totalQuantity, \n" +
                            " ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
                            " FROM trade t \n" +
                            " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                            " WHERE \n" +
                            " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                            " AND e.name = '"+chartDataDTO.getExporterName()+"' AND t.country = '"+chartDataDTO.getImporterCountry()+"' \n" +
                            " AND t.quantity_in_tons IS NOT NULL \n" +
                            " GROUP BY TO_CHAR(t.bill_date, 'Month') " +
                            " ORDER BY index ";
                } else if (chartDataDTO.getTabName().equalsIgnoreCase(Constants.IMPORT_TAB)) {
                    // IMPORTER LINE CHART QUERY
                    LINE_CHART_DATA_QUERY = " SELECT EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
                            " TO_CHAR(t.bill_date, 'Month') AS monthName, ROUND((SUM(t.quantity_in_tons)),2) AS totalQuantity, \n" +
                            " ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
                            " FROM trade t \n" +
                            " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                            " WHERE \n" +
                            " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                            " AND e.name = '"+chartDataDTO.getExporterName()+"' AND t.country = '"+chartDataDTO.getImporterCountry()+"' \n" +
                            " AND t.quantity_in_tons IS NOT NULL \n" +
                            " GROUP BY TO_CHAR(t.bill_date, 'Month') " +
                            " ORDER BY index ";
                } else if (chartDataDTO.getTabName().equalsIgnoreCase(Constants.CHEMICAL_TAB)
                        && FwUtils.isNotBlankOrNullString(chartDataDTO.getChemicalName()) && FwUtils.hasId(chartDataDTO.getRitcCode())) {
                    // CHEMICAL LINE CHART QUERY
                    LINE_CHART_DATA_QUERY = "SELECT EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
                            " TO_CHAR(t.bill_date, 'Month') AS monthName, ROUND((SUM(t.quantity_in_tons)),2) AS totalQuantity \n" +
                            " FROM trade t INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                            " INNER JOIN importer i ON t.importer_id = i.id \n" +
                            " INNER JOIN chemical c ON t.ritc_code = t.ritc_code \n" +
                            " WHERE \n" +
                            " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                            " AND e.name = '"+chartDataDTO.getExporterName()+"' AND t.country = '"+chartDataDTO.getImporterCountry()+"' \n" +
                            " AND t.quantity_in_tons IS NOT NULL \n" +
                            " AND c.name = '"+chartDataDTO.getChemicalName()+"' AND t.ritc_code = "+chartDataDTO.getRitcCode()+" \n" +
                            " GROUP BY TO_CHAR(t.bill_date, 'Month') \n" +
                            " ORDER BY index ";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return LINE_CHART_DATA_QUERY;

    }

    public static String getTopFiveLineChartDataQuery(ChartDataDTO chartDataDTO){
        String TOP_FIVE_LINE_CHART_QUERY = "";
        try{
            if(Objects.nonNull(chartDataDTO) && FwUtils.isNotBlankOrNullString(chartDataDTO.getTabName())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())){
                if(chartDataDTO.getTabName().equalsIgnoreCase(Constants.EXPORT_TAB)){

                    TOP_FIVE_LINE_CHART_QUERY = getTopFiveChemicalExportLineChart(chartDataDTO);
                } else if (chartDataDTO.getTabName().equalsIgnoreCase(Constants.IMPORT_TAB)) {

                    TOP_FIVE_LINE_CHART_QUERY = getTopFiveImporterLineChartQuery(chartDataDTO);
                } else if (chartDataDTO.getTabName().equalsIgnoreCase(Constants.CHEMICAL_TAB)
                        && FwUtils.isNotBlankOrNullString(chartDataDTO.getChemicalName()) && FwUtils.hasId(chartDataDTO.getRitcCode())) {

                    TOP_FIVE_LINE_CHART_QUERY = getTopFivChemicalLineChartQuery(chartDataDTO);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return TOP_FIVE_LINE_CHART_QUERY;
    }

    public static String getTopFiveChemicalExportLineChart(ChartDataDTO chartDataDTO){
        String TOP_FIVE_CHEMICAL_QUANTITY = "";
        try{
            if(Objects.nonNull(chartDataDTO)
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())){

                TOP_FIVE_CHEMICAL_QUANTITY = "SELECT TRIM(c.name) AS entityName,EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
                        " TO_CHAR(t.bill_date, 'Month') AS monthName, \n" +
                        " ROUND((SUM(t.quantity_in_tons)),2) AS totalQuantity , \n" +
                        " ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
                        " FROM trade t \n" +
                        " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                        " INNER JOIN importer i ON t.importer_id = i.id \n" +
                        " INNER JOIN chemical c ON t.ritc_code = t.ritc_code \n" +
                        " WHERE \n" +
                        " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                        " AND e.name ='"+chartDataDTO.getExporterName()+"' AND t.country='"+chartDataDTO.getImporterCountry()+"' \n" +
                        " AND c.name IN (SELECT c.name \n" +
                        " FROM trade t \n" +
                        " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                        " INNER JOIN importer i ON t.importer_id = i.id \n" +
                        " INNER JOIN chemical c ON t.ritc_code = t.ritc_code \n" +
                        " WHERE \n" +
                        " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                        " AND e.name ='"+chartDataDTO.getExporterName()+"' AND t.country='"+chartDataDTO.getImporterCountry()+"' \n" +
                        " GROUP BY  c.name \n" +
                        " ORDER BY ROUND(SUM(t.quantity_in_tons),2) DESC \n" +
                        " LIMIT 5) \n" +
                        " GROUP BY entityName,TO_CHAR(t.bill_date, 'Month') \n" +
                        " ORDER BY entityName,index ";

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return TOP_FIVE_CHEMICAL_QUANTITY;
    }

    public static String getTopFiveImporterLineChartQuery(ChartDataDTO chartDataDTO){
        String TOP_FIVE_IMPORTER_QUANTITY = "";
        try{
            if(Objects.nonNull(chartDataDTO)
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())){

                TOP_FIVE_IMPORTER_QUANTITY = "SELECT TRIM(i.name) AS entityName,EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
                        " TO_CHAR(t.bill_date, 'Month') AS monthName, \n" +
                        " ROUND((SUM(t.quantity_in_tons)),2) AS totalQuantity \n" +
                        " FROM trade t \n" +
                        " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                        " INNER JOIN importer i ON t.importer_id = i.id \n" +
                        " INNER JOIN chemical c ON t.ritc_code = t.ritc_code \n" +
                        " WHERE \n" +
                        " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                        " AND e.name ='"+chartDataDTO.getExporterName()+"' AND t.country='"+chartDataDTO.getImporterCountry()+"' \n" +
                        " AND i.name IN ( SELECT i.name \n" +
                        " FROM trade t \n" +
                        " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                        " INNER JOIN importer i ON t.importer_id = i.id \n" +
                        " WHERE \n" +
                        " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                        " AND e.name ='"+chartDataDTO.getExporterName()+"' AND t.country='"+chartDataDTO.getImporterCountry()+"' \n" +
                        " GROUP BY  i.id,i.name \n" +
                        " ORDER BY ROUND(SUM(t.quantity_in_tons),2) DESC \n" +
                        " LIMIT 5) \n" +
                        " GROUP BY entityName,TO_CHAR(t.bill_date, 'Month') \n" +
                        " ORDER BY entityName,index ";

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return TOP_FIVE_IMPORTER_QUANTITY;
    }

    public static String getTopFivChemicalLineChartQuery(ChartDataDTO chartDataDTO){
        String TOP_FIVE_CHEMICAL_LINE_CHART = "";
        try{
            if(Objects.nonNull(chartDataDTO)
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getChemicalName()) && FwUtils.hasId(chartDataDTO.getRitcCode())){

                TOP_FIVE_CHEMICAL_LINE_CHART = " SELECT TRIM(i.name) AS entityName,EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
                        " TO_CHAR(t.bill_date, 'Month') AS monthName, \n" +
                        " ROUND((SUM(t.quantity_in_tons)),2) AS totalQuantity \n" +
                        " FROM trade t INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                        " INNER JOIN importer i ON t.importer_id = i.id \n" +
                        " INNER JOIN chemical c ON t.ritc_code = t.ritc_code \n" +
                        " WHERE \n" +
                        " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                        " AND e.name = '"+chartDataDTO.getExporterName()+"' AND t.country = '"+chartDataDTO.getImporterCountry()+"' \n" +
                        " AND t.quantity_in_tons IS NOT NULL \n" +
                        " AND c.name = '"+chartDataDTO.getChemicalName()+"' AND t.ritc_code = "+chartDataDTO.getRitcCode()+" \n" +
                        " AND i.name IN ( SELECT i.name FROM trade t \n" +
                        " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                        " INNER JOIN importer i ON t.importer_id = i.id \n" +
                        " INNER JOIN chemical c ON t.ritc_code = t.ritc_code \n" +
                        " WHERE \n" +
                        " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                        " AND e.name = '"+chartDataDTO.getExporterName()+"' AND t.country = '"+chartDataDTO.getImporterCountry()+"' \n" +
                        " AND t.quantity_in_tons IS NOT NULL \n" +
                        " AND c.name = '"+chartDataDTO.getChemicalName()+"' AND t.ritc_code = "+chartDataDTO.getRitcCode()+" \n" +
                        " GROUP BY i.id,i.name order by ROUND(SUM(t.quantity_in_tons),2) DESC LIMIT 5 ) \n" +
                        " GROUP BY entityName,TO_CHAR(t.bill_date, 'Month') \n" +
                        " ORDER BY entityName,index ";

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return TOP_FIVE_CHEMICAL_LINE_CHART;
    }

    // BAR CHART DATA QUERY
    public static String getBarChartDataQuery(ChartDataDTO chartDataDTO){
        String BAR_CHART_DATA_QUERY = "";
        try{
            if(Objects.nonNull(chartDataDTO) && FwUtils.isNotBlankOrNullString(chartDataDTO.getTabName())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())){
                if(chartDataDTO.getTabName().equalsIgnoreCase(Constants.EXPORT_TAB)){
                    // CHEMICAL EXPORT BAR CHART QUERY
                    BAR_CHART_DATA_QUERY = " SELECT EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
                            " TO_CHAR(t.bill_date, 'Month') AS monthName, \n" +
                            " ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
                            " FROM trade t INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                            " INNER JOIN importer i ON t.importer_id = i.id \n" +
                            " INNER JOIN chemical c ON t.ritc_code = t.ritc_code \n" +
                            " WHERE \n" +
                            " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                            " AND e.name ='"+chartDataDTO.getExporterName()+"' AND t.country='"+chartDataDTO.getImporterCountry()+"' \n" +
                            " AND t.quantity_in_tons IS NOT NULL \n" +
                            " GROUP BY TO_CHAR(t.bill_date, 'Month') \n" +
                            " ORDER BY index ";
                } else if (chartDataDTO.getTabName().equalsIgnoreCase(Constants.IMPORT_TAB)) {
                    // IMPORTER BAR CHART QUERY
                    BAR_CHART_DATA_QUERY = " SELECT EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
                            " TO_CHAR(t.bill_date, 'Month') AS monthName, \n" +
                            " ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
                            " FROM trade t INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                            " INNER JOIN importer i ON t.importer_id = i.id \n" +
                            " INNER JOIN chemical c ON t.ritc_code = t.ritc_code \n" +
                            " WHERE \n" +
                            " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                            " AND e.name ='"+chartDataDTO.getExporterName()+"' AND t.country='"+chartDataDTO.getImporterCountry()+"' \n" +
                            " AND t.quantity_in_tons IS NOT NULL \n" +
                            " GROUP BY TO_CHAR(t.bill_date, 'Month') \n" +
                            " ORDER BY index ";
                } else if (chartDataDTO.getTabName().equalsIgnoreCase(Constants.CHEMICAL_TAB)
                        && FwUtils.isNotBlankOrNullString(chartDataDTO.getChemicalName()) && FwUtils.hasId(chartDataDTO.getRitcCode())) {
                    // CHEMICAL BAR CHART QUERY
                    BAR_CHART_DATA_QUERY = "SELECT EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
                            " TO_CHAR(t.bill_date, 'Month') AS monthName, ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
                            " FROM trade t INNER JOIN exporter e ON t.exporter_id = e.id\n" +
                            " INNER JOIN importer i ON t.importer_id = i.id\n" +
                            " INNER JOIN chemical c ON t.ritc_code = t.ritc_code\n" +
                            " WHERE \n" +
                            " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                            " AND e.name ='"+chartDataDTO.getExporterName()+"' AND t.country='"+chartDataDTO.getImporterCountry()+"' \n" +
                            " AND t.quantity_in_tons IS NOT NULL \n" +
                            " AND c.name='"+chartDataDTO.getChemicalName()+"' AND t.ritc_code= "+chartDataDTO.getRitcCode()+" \n" +
                            " GROUP BY TO_CHAR(t.bill_date, 'Month') \n" +
                            " ORDER BY index ";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return BAR_CHART_DATA_QUERY;
    }

    public static String getTopFiveBarChartDataQuery(ChartDataDTO chartDataDTO){
        String TOP_FIVE_BAR_CHART_QUERY = "";
        try{
            if(Objects.nonNull(chartDataDTO) && FwUtils.isNotBlankOrNullString(chartDataDTO.getTabName())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())){
                if(chartDataDTO.getTabName().equalsIgnoreCase(Constants.EXPORT_TAB)){

                    TOP_FIVE_BAR_CHART_QUERY = getTopFiveChemicalExportBarChartQuery(chartDataDTO);
                } else if (chartDataDTO.getTabName().equalsIgnoreCase(Constants.IMPORT_TAB)) {

                    TOP_FIVE_BAR_CHART_QUERY = getTopFiveImporterBarChartQuery(chartDataDTO);
                } else if (chartDataDTO.getTabName().equalsIgnoreCase(Constants.CHEMICAL_TAB)
                        && FwUtils.isNotBlankOrNullString(chartDataDTO.getChemicalName()) && FwUtils.hasId(chartDataDTO.getRitcCode())) {
                    TOP_FIVE_BAR_CHART_QUERY = getTopFivChemicalBarChartQuery(chartDataDTO);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return TOP_FIVE_BAR_CHART_QUERY;
    }

    public static String getTopFiveChemicalExportBarChartQuery(ChartDataDTO chartDataDTO){
        String TOP_FIVE_CHEMICAL_EXPORT_AMOUNT = "";
        try{
            if(Objects.nonNull(chartDataDTO)
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())){

                TOP_FIVE_CHEMICAL_EXPORT_AMOUNT = " SELECT TRIM(c.name) AS entityName,EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
                        " TO_CHAR(t.bill_date, 'Month') AS monthName, \n" +
                        " ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
                        " FROM trade t \n" +
                        " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                        " INNER JOIN importer i ON t.importer_id = i.id \n" +
                        " INNER JOIN chemical c ON t.ritc_code = t.ritc_code \n" +
                        " WHERE \n" +
                        " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                        " AND e.name ='"+chartDataDTO.getExporterName()+"' AND t.country='"+chartDataDTO.getImporterCountry()+"' \n" +
                        " AND c.name IN (SELECT c.name \n" +
                        " FROM trade t \n" +
                        " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                        " INNER JOIN importer i ON t.importer_id = i.id \n" +
                        " INNER JOIN chemical c ON t.ritc_code = t.ritc_code \n" +
                        " WHERE \n" +
                        " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                        " AND e.name ='"+chartDataDTO.getExporterName()+"' AND t.country='"+chartDataDTO.getImporterCountry()+"' \n" +
                        " GROUP BY  c.name \n" +
                        " ORDER BY ROUND((SUM(t.total_fob_inr)/1000),2) DESC \n" +
                        " LIMIT 5) \n" +
                        " GROUP BY entityName,TO_CHAR(t.bill_date, 'Month') \n" +
                        " ORDER BY entityName,index ";

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return TOP_FIVE_CHEMICAL_EXPORT_AMOUNT;
    }

    public static String getTopFiveImporterBarChartQuery(ChartDataDTO chartDataDTO){
        String TOP_FIVE_IMPORTER_AMOUNT = "";
        try{
            if(Objects.nonNull(chartDataDTO)
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())){

                TOP_FIVE_IMPORTER_AMOUNT = " SELECT TRIM(i.name) AS entityName,EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
                        " TO_CHAR(t.bill_date, 'Month') AS monthName, \n" +
                        " ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
                        " FROM trade t \n" +
                        " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                        " INNER JOIN importer i ON t.importer_id = i.id \n" +
                        " INNER JOIN chemical c ON t.ritc_code = t.ritc_code \n" +
                        " WHERE \n" +
                        " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                        " AND e.name ='"+chartDataDTO.getExporterName()+"' AND t.country='"+chartDataDTO.getImporterCountry()+"' \n" +
                        " AND i.name IN ( SELECT i.name \n" +
                        " FROM trade t \n" +
                        " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                        " INNER JOIN importer i ON t.importer_id = i.id \n" +
                        " WHERE \n" +
                        " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                        " AND e.name ='"+chartDataDTO.getExporterName()+"' AND t.country='"+chartDataDTO.getImporterCountry()+"' \n" +
                        " GROUP BY  i.id,i.name \n" +
                        " ORDER BY ROUND((SUM(t.total_fob_inr)/1000),2) DESC \n" +
                        " LIMIT 5) \n" +
                        " GROUP BY entityName,TO_CHAR(t.bill_date, 'Month') \n" +
                        " ORDER BY entityName,index ";

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return TOP_FIVE_IMPORTER_AMOUNT;
    }

    public static String getTopFivChemicalBarChartQuery(ChartDataDTO chartDataDTO){
        String TOP_FIVE_CHEMICAL_LINE_CHART = "";
        try{
            if(Objects.nonNull(chartDataDTO)
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(chartDataDTO.getToDate())
                    && FwUtils.isNotBlankOrNullString(chartDataDTO.getChemicalName()) && FwUtils.hasId(chartDataDTO.getRitcCode())){

                TOP_FIVE_CHEMICAL_LINE_CHART = " SELECT TRIM(i.name) AS entityName,EXTRACT(MONTH FROM TO_DATE(TO_CHAR(t.bill_date, 'Month'), 'Month')) AS index, \n" +
                        " TO_CHAR(t.bill_date, 'Month') AS monthName, \n" +
                        " ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
                        " FROM trade t INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                        " INNER JOIN importer i ON t.importer_id = i.id \n" +
                        " INNER JOIN chemical c ON t.ritc_code = t.ritc_code \n" +
                        " WHERE \n" +
                        " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                        " AND e.name = '"+chartDataDTO.getExporterName()+"' AND t.country = '"+chartDataDTO.getImporterCountry()+"' \n" +
                        " AND t.quantity_in_tons IS NOT NULL \n" +
                        " AND c.name = '"+chartDataDTO.getChemicalName()+"' AND t.ritc_code = "+chartDataDTO.getRitcCode()+" \n" +
                        " AND i.name IN ( SELECT i.name FROM trade t \n" +
                        " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                        " INNER JOIN importer i ON t.importer_id = i.id \n" +
                        " INNER JOIN chemical c ON t.ritc_code = t.ritc_code \n" +
                        " WHERE \n" +
                        " t.bill_date BETWEEN to_date('"+chartDataDTO.getFromDate()+"','YYYY-MM') AND to_date('"+chartDataDTO.getToDate()+"','YYYY-MM') \n" +
                        " AND e.name = '"+chartDataDTO.getExporterName()+"' AND t.country = '"+chartDataDTO.getImporterCountry()+"' \n" +
                        " AND t.quantity_in_tons IS NOT NULL \n" +
                        " AND c.name = '"+chartDataDTO.getChemicalName()+"' AND t.ritc_code = "+chartDataDTO.getRitcCode()+" \n" +
                        " GROUP BY i.id,i.name order by ROUND(SUM(t.quantity_in_tons),2) DESC LIMIT 5 ) \n" +
                        " GROUP BY entityName,TO_CHAR(t.bill_date, 'Month') \n" +
                        " ORDER BY entityName,index ";

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return TOP_FIVE_CHEMICAL_LINE_CHART;
    }

}
