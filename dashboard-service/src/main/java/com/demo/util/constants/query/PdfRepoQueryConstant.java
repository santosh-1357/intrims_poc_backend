package com.demo.util.constants.query;

import com.demo.domian.dto.DashboardDTO;
import common.library.constants.app.FwUtils;

import java.util.Objects;

public interface PdfRepoQueryConstant {

    public static String getImporterTableQuery(DashboardDTO dashboardDTO){
        StringBuilder builder = new StringBuilder();
        try{
            if(Objects.nonNull(dashboardDTO) && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getImporterCountry())){
                builder.append(" SELECT TRIM(i.name) AS importerName, TRIM(t.country) AS importerCountry, \n" +
                        " STRING_AGG (to_char(t.bill_date,'YYYY-MM-DD'),' , ' ORDER BY t.bill_date ) AS dateOfTransaction, \n" +
                        " STRING_AGG (t.bill_no,' , ' ORDER BY t.bill_no ) AS exportReference \n" +
                        " FROM trade t \n" +
                        " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                        " INNER JOIN importer i ON t.importer_id = i.id \n" +
                        " INNER JOIN chemical c ON t.ritc_code = c.ritc_code \n" +
                        " WHERE t.bill_date BETWEEN to_date( '"+dashboardDTO.getFromDate()+"' ,'YYYY-MM') AND to_date( '"+dashboardDTO.getToDate()+"' ,'YYYY-MM') \n" +
                        " AND e.name= '"+dashboardDTO.getExporterName()+"' AND t.country='"+dashboardDTO.getImporterCountry()+"' \n" +
                        " AND t.total_fob_inr IS NOT NULL ");
                if(FwUtils.isNotBlankOrNullString(dashboardDTO.getChemicalName()) && FwUtils.isNotBlankOrNullString(String.valueOf(dashboardDTO.getRitcCode()))
                        && !dashboardDTO.getChemicalName().equalsIgnoreCase("All")){
                    builder.append(" AND c.name = '"+dashboardDTO.getChemicalName()+"' AND c.ritc_code = "+dashboardDTO.getRitcCode() +" \n");
                }
                builder.append(" GROUP BY i.id,importerName,importerCountry ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static String getChemicalTableQuery(DashboardDTO dashboardDTO){
        StringBuilder builder = new StringBuilder();
        try{
            if(Objects.nonNull(dashboardDTO) && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getImporterCountry())){
                builder.append(" SELECT TRIM(c.name) AS casNumber, t.ritc_code AS ecNumber,TRIM(t.country) AS region, \n" +
                        " TRIM(e.iec) AS registrationNumber, '-' AS initialTonnage,'Yes' AS exportStatus, \n" +
                        " ROUND((SUM(t.quantity_in_tons)),2) AS increaseTonnage,'-' AS tonnageBand \n" +
                        " FROM trade t \n" +
                        " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
                        " INNER JOIN importer i ON t.importer_id = i.id \n" +
                        " INNER JOIN chemical c ON t.ritc_code = c.ritc_code \n" +
                        " WHERE t.quantity_in_tons IS NOT NULL AND t.uqc IN('LTR','GMS','PCS','NOS','MTS','KGS') \n" +
                        " AND \n" +
                        " t.bill_date BETWEEN to_date( '"+dashboardDTO.getFromDate()+"' ,'YYYY-MM') AND to_date( '"+dashboardDTO.getToDate()+"' ,'YYYY-MM') \n" +
                        " AND e.name= '"+dashboardDTO.getExporterName()+"' AND t.country='"+dashboardDTO.getImporterCountry()+"' \n");
                if(FwUtils.isNotBlankOrNullString(dashboardDTO.getChemicalName()) && FwUtils.isNotBlankOrNullString(String.valueOf(dashboardDTO.getRitcCode()))
                        && !dashboardDTO.getChemicalName().equalsIgnoreCase("All")){
                    builder.append(" AND c.name = '"+dashboardDTO.getChemicalName()+"' AND c.ritc_code = "+dashboardDTO.getRitcCode() +" \n");
                }
                builder.append(" GROUP BY c.name,t.ritc_code,t.country,registrationNumber ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return builder.toString();
    }

}
