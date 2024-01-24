package com.demo.repository;

import com.demo.domian.DropDownResponse;
import com.demo.domian.dto.*;
import com.demo.util.constants.query.DashboardRepoQueryConstants;
import com.demo.util.constants.query.PdfRepoQueryConstant;
import common.library.constants.app.FwUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class PdfRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public DropDownResponse getDropDownData(DashboardDTO dashboardDTO) throws Exception {
        DropDownResponse dropDownResponse = null;
        try{
            // Check if the provided DashboardDTO is not null and contains required parameters
            if(Objects.nonNull(dashboardDTO) && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())){
                // Initialize the DropDownResponse
                dropDownResponse = new DropDownResponse();
                // Query for country-wise count data
                List<CountryWiseCount> countryList = jdbcTemplate.query(DashboardRepoQueryConstants.COUNTRY_LIST_QUERY, new Object[] {
                                dashboardDTO.getFromDate(), dashboardDTO.getToDate(), dashboardDTO.getExporterName()
                        },
                        new BeanPropertyRowMapper<CountryWiseCount>(CountryWiseCount.class));

                // Query for chemical count data
                List<ChemicalDTO> chemicalList = jdbcTemplate.query(DashboardRepoQueryConstants.CHEMICAL_COUNT_QUERY, new Object[] {
                                dashboardDTO.getFromDate(), dashboardDTO.getToDate(), dashboardDTO.getExporterName()
                        },
                        new BeanPropertyRowMapper<ChemicalDTO>(ChemicalDTO.class));
                // Check if both country and chemical lists are not empty
                if(FwUtils.isNotNullOrNotEmpty(countryList) && FwUtils.isNotNullOrNotEmpty(chemicalList)){
                    //set country and chemical lists in response object
                    dropDownResponse.setCountryList(countryList);
                    dropDownResponse.setChemicalList(chemicalList);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        // Return the retrieved DropDownResponse object
        return dropDownResponse;
    }

    public List<ImporterTable> getImporterTableData(DashboardDTO dashboardDTO){
        List<ImporterTable> importerList = null;
        try{
            // Check if the provided DashboardDTO is not null and contains required parameters
            if(Objects.nonNull(dashboardDTO) && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())){
                // Query for importer table data using the provided DashboardDTO
                importerList = jdbcTemplate.query(PdfRepoQueryConstant.getImporterTableQuery(dashboardDTO),
                        new Object[] { },
                        new BeanPropertyRowMapper<ImporterTable>(ImporterTable.class));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        // Return the retrieved list of ImporterTable objects
        return importerList;
    }

    public List<ChemicalTable> getChemicalTableData(DashboardDTO dashboardDTO){
        List<ChemicalTable> chemicalList =null;
        try{
            // Check if the input DashboardDTO and its required fields are not null or blank
            if(Objects.nonNull(dashboardDTO) && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getImporterCountry()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getChemicalName())){
                // Execute a database query using jdbcTemplate with the provided query and parameters
                chemicalList = jdbcTemplate.query(PdfRepoQueryConstant.getChemicalTableQuery(dashboardDTO),
                        new Object[] { },
                        new BeanPropertyRowMapper<ChemicalTable>(ChemicalTable.class));
            }
            // Check if the chemicalList is not null and the ChemicalName is "All"
            if(FwUtils.isNotNullOrNotEmpty(chemicalList) && FwUtils.isNotBlankOrNullString(dashboardDTO.getChemicalName())
                && dashboardDTO.getChemicalName().equalsIgnoreCase("All")){
                // Add default ChemicalTable objects to the list
                chemicalList.add(
                        ChemicalTable
                                .builder()
                                .casNumber("ABC")
                                .ecNumber("123")
                                .region(dashboardDTO.getImporterCountry())
                                .registrationNumber("987654")
                                .initialTonnage("-")
                                .exportStatus("No")
                                .increaseTonnage("0")
                                .tonnageBand("-")
                                .build()
                );

                chemicalList.add(
                        ChemicalTable
                                .builder()
                                .casNumber("DEF")
                                .ecNumber("456")
                                .region(dashboardDTO.getImporterCountry())
                                .registrationNumber("091276")
                                .initialTonnage("-")
                                .exportStatus("No")
                                .increaseTonnage("0")
                                .tonnageBand("-")
                                .build()
                );

                chemicalList.add(
                        ChemicalTable
                                .builder()
                                .casNumber("wxyz")
                                .ecNumber("45698")
                                .region(dashboardDTO.getImporterCountry())
                                .registrationNumber("5649091276")
                                .initialTonnage("-")
                                .exportStatus("No")
                                .increaseTonnage("0")
                                .tonnageBand("-")
                                .build()
                );

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // Return the list of ChemicalTable objects
        return chemicalList;
    }


}
