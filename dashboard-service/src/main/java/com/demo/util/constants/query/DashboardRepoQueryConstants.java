package com.demo.util.constants.query;

import com.demo.domian.dto.DashboardDTO;
import common.library.constants.app.FwUtils;

import java.util.Objects;

public interface DashboardRepoQueryConstants {

    String COUNTRY_LIST_QUERY =" SELECT DISTINCT TRIM(t.country) AS countryName , \n" +
            " COUNT(DISTINCT t.ritc_code) AS chemicalCount ,COUNT(DISTINCT t.importer_id) AS importerCount, \n" +
            " ROUND((SUM(t.quantity_in_tons)),2) as totalQuantity , ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
            " FROM trade t \n" +
            " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
            " INNER JOIN importer i ON t.importer_id = i.id \n" +
            " WHERE \n" +
            " t.bill_date BETWEEN to_date( ? ,'YYYY-MM') AND to_date( ? ,'YYYY-MM') \n" +
            " AND t.quantity_in_tons IS NOT NULL AND t.uqc IN('LTR','GMS','PCS','NOS','MTS','KGS') \n" +
            " AND e.name = ? \n" +
            " GROUP BY t.country ";

    String CHEMICAL_COUNT_QUERY = " SELECT DISTINCT TRIM(name) AS chemicalName, ritc_code AS chemicalCode FROM chemical WHERE ritc_code IN ( \n" +
            " SELECT DISTINCT t.ritc_code AS name \n" +
            " FROM trade t\n" +
            " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
            " WHERE \n" +
            " t.bill_date BETWEEN to_date(?,'YYYY-MM') AND to_date(?,'YYYY-MM') \n" +
            " AND t.quantity_in_tons IS NOT NULL AND t.uqc IN('LTR','GMS','PCS','NOS','MTS','KGS') \n" +
            " AND e.name = ? \n" +
            " GROUP BY t.ritc_code ) ";

    String IMPORTER_LIST_QUERY =" SELECT DISTINCT  t.importer_id AS id, i.name AS name , ROUND((SUM(t.quantity_in_tons)),2) AS quantity \n" +
            " FROM trade t \n" +
            " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
            " INNER JOIN importer i on t.importer_id = i.id \n" +
            " WHERE \n" +
            " t.bill_date BETWEEN to_date( ? ,'YYYY-MM') AND to_date( ? ,'YYYY-MM') \n" +
            " AND t.quantity_in_tons IS NOT NULL AND t.uqc IN('LTR','GMS','PCS','NOS','MTS','KGS') \n" +
            " AND e.name = ? \n" +
            " GROUP BY t.importer_id,i.name ";

    String CHEMICAL_EXPORT_SELECT_QUERY = " SELECT TRIM(c.name) AS chemicalName, \n" +
            " t.ritc_code AS chemicalCode, COUNT( DISTINCT t.importer_id) AS totalImporter, \n" +
            " ROUND((SUM(t.quantity_in_tons)),2) AS totalQuantity, ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n";
    String CHEMICAL_EXPORT_COUNT_QUERY = " SELECT DISTINCT c.name as chemicalName,t.ritc_code AS chemicalCode ";

    String CHEMICAL_EXPORT_GROUP_BY_QUERY = " GROUP BY t.ritc_code,c.name ";

    String CHEMICAL_IMPORT_SELECT_QUERY = " SELECT DISTINCT  i.id AS importerId,TRIM(i.name) AS importerName, COUNT( DISTINCT t.ritc_code) AS chemicalCount, \n" +
            " ROUND((SUM(t.quantity_in_tons)),2) AS totalQuantity, ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n";

    String CHEMICAL_IMPORT_COUNT_QUERY = " SELECT DISTINCT i.id AS importerId,i.name AS importerName ";

    String CHEMICAL_IMPORT_GROUP_BY_QUERY = " GROUP BY i.id,i.name ";


    String FROM_QUERY = " FROM trade t \n" +
            " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
            " INNER JOIN importer i ON t.importer_id = i.id \n" +
            " INNER JOIN chemical c ON t.ritc_code = c.ritc_code \n";

    String COUNTRY_WISE_TOTAL_COUNT = " SELECT TRIM(t.country) AS countryName , COUNT( DISTINCT t.ritc_code) AS chemicalCount, COUNT( DISTINCT t.importer_id) AS importerCount, \n" +
            " ROUND((SUM(t.quantity_in_tons)),2) AS totalQuantity, ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n" +
            " FROM trade t \n" +
            " INNER JOIN exporter e ON t.exporter_id = e.id \n" +
            " INNER JOIN importer i ON t.importer_id = i.id \n" +
            " WHERE t.bill_date BETWEEN to_date( ? ,'YYYY-MM') AND to_date( ? ,'YYYY-MM') \n" +
            " AND t.quantity_in_tons IS NOT NULL AND t.uqc IN('LTR','GMS','PCS','NOS','MTS','KGS') \n" +
            " AND e.name = ? AND t.country = ? " +
            " GROUP BY t.country ";

    String COUNTRY_CHEMICAL_DATA_SELECT_QUERY = " SELECT DISTINCT TRIM(i.name) AS importerName, ROUND((SUM(t.quantity_in_tons)),2) AS totalQuantity , \n" +
            " ROUND((SUM(t.total_fob_inr)/1000),2) AS totalAmount \n";
    String COUNTRY_CHEMICAL_DATA_COUNT_QUERY = "SELECT DISTINCT TRIM(i.name) AS importerName ";

    String COUNTRY_CHEMICAL_DATA_GROUP_BY = " GROUP BY importerName ";

    public static String getFromAndWhereQuery(DashboardDTO dashboardDTO){
        StringBuilder stringBuilder = new StringBuilder(FROM_QUERY);
        try{
            if(Objects.nonNull(dashboardDTO)
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())){
                stringBuilder.append(" WHERE 1=1 ");
                stringBuilder.append(" AND t.quantity_in_tons IS NOT NULL AND t.uqc IN('LTR','GMS','PCS','NOS','MTS','KGS') ");
                stringBuilder.append( " AND t.bill_date BETWEEN to_date( '"+dashboardDTO.getFromDate()+"' ,'YYYY-MM') AND to_date( '"+dashboardDTO.getToDate()+"' ,'YYYY-MM') \n" +
                " AND e.name = '"+dashboardDTO.getExporterName()+"' AND t.country = '"+dashboardDTO.getImporterCountry()+"' ");

                //country/chemical API
                if(FwUtils.isNotBlankOrNullString(dashboardDTO.getChemicalName()) && FwUtils.hasId(dashboardDTO.getRitcCode())){
                    stringBuilder.append("AND c.name = '"+dashboardDTO.getChemicalName()+"' AND t.ritc_code = "+dashboardDTO.getRitcCode()+" ");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String getCountQuery(String query){
        StringBuilder stringBuilder = new StringBuilder();
        try{
            if(FwUtils.isNotBlankOrNullString(query)){
                stringBuilder.append(" SELECT COUNT(a.*) AS totalRecords FROM ( \n");
                stringBuilder.append(query);
                stringBuilder.append(" ) AS a ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
