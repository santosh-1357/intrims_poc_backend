package com.demo.repository;

import com.demo.domian.dto.*;
import com.demo.domian.*;
import com.demo.util.constants.query.DashboardRepoQueryConstants;
import common.library.constants.app.FwUtils;
import common.library.constants.query.QueryUtils;
import common.library.pagination.options.GridOptions;
import common.library.pagination.response.GridSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class DashboardRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    MetadataRepo metadataRepo;

    public DashboardResponse getExportData(DashboardDTO dashboardDTO) {
        DashboardResponse dashboardResponse = null;
        try{
            // Check if the input DashboardDTO and its required fields are not null or blank
            if(Objects.nonNull(dashboardDTO) && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())){
                dashboardResponse = new DashboardResponse();

                // Query to retrieve a list of CountryWiseCount objects based on the provided parameters
                List<CountryWiseCount> countryList = jdbcTemplate.query(DashboardRepoQueryConstants.COUNTRY_LIST_QUERY, new Object[] {
                        dashboardDTO.getFromDate(), dashboardDTO.getToDate(), dashboardDTO.getExporterName()
                        },
                        new BeanPropertyRowMapper<CountryWiseCount>(CountryWiseCount.class));
                // Check if the countryList is not null and not empty
                if(FwUtils.isNotNullOrNotEmpty(countryList)){
                    // Set the country count and list in the DashboardResponse object
                    dashboardResponse.setCountryCount(countryList.size());
                    dashboardResponse.setCountryList(countryList);
                }

                // Query to retrieve a list of ChemicalDTO objects based on the provided parameters
                List<ChemicalDTO> chemicalList = jdbcTemplate.query(DashboardRepoQueryConstants.CHEMICAL_COUNT_QUERY, new Object[] {
                                dashboardDTO.getFromDate(), dashboardDTO.getToDate(), dashboardDTO.getExporterName()
                        },
                        new BeanPropertyRowMapper<ChemicalDTO>(ChemicalDTO.class));
                // Check if the chemicalList is not null and not empty
                if(FwUtils.isNotNullOrNotEmpty(chemicalList)){
                    // Set the chemical count in the DashboardResponse object
                    dashboardResponse.setChemicalCount(chemicalList.size());
                }

                // Query to retrieve a list of CountDTO objects representing importers based on the provided parameters
                List<CountDTO> importerList = jdbcTemplate.query(DashboardRepoQueryConstants.IMPORTER_LIST_QUERY, new Object[] {
                                dashboardDTO.getFromDate(), dashboardDTO.getToDate(), dashboardDTO.getExporterName()
                        },
                        new BeanPropertyRowMapper<CountDTO>(CountDTO.class));
                // Check if the importerList is not null and not empty
                if(FwUtils.isNotNullOrNotEmpty(importerList)){
                    // Set the buyers count in the DashboardResponse object
                    dashboardResponse.setBuyersCount(importerList.size());
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // Return the populated DashboardResponse object
        return dashboardResponse;
    }

    public GridSearchResponse<ExportsResponse> getCountryExportsData(GridOptions<DashboardDTO> gridOptions) {
        GridSearchResponse<ExportsResponse> searchResponse = new GridSearchResponse<>();

        try {
            // Check if gridOptions and its metadata are not null, and required fields are not blank
            if(Objects.nonNull(gridOptions) && Objects.nonNull(gridOptions.getMetadata())
                    && FwUtils.isNotBlankOrNullString(gridOptions.getMetadata().getExporterName()) && FwUtils.isNotBlankOrNullString(gridOptions.getMetadata().getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(gridOptions.getMetadata().getFromDate()) && FwUtils.isNotBlankOrNullString(gridOptions.getMetadata().getToDate())){
                // Build the SELECT part of the query
                StringBuilder sbQuery = new StringBuilder(DashboardRepoQueryConstants.CHEMICAL_EXPORT_SELECT_QUERY);
                // Build the FROM and WHERE part of the query using metadata and search filters
                StringBuilder fromQuery = new StringBuilder(DashboardRepoQueryConstants.getFromAndWhereQuery(gridOptions.getMetadata()));
                fromQuery.append(QueryUtils.getSearchFilter(gridOptions.getSearchFilters()));

                // Combine the SELECT, FROM and WHERE parts of the query
                sbQuery.append(fromQuery);
                // GROUP BY QUERY
                sbQuery.append(DashboardRepoQueryConstants.CHEMICAL_EXPORT_GROUP_BY_QUERY);
                // Add sorting and pagination to the query
                sbQuery.append(QueryUtils.getSortOrder(gridOptions.getSortFilters()));
                sbQuery.append(QueryUtils.getLimitOffset(gridOptions.getPaging()));

                // Build the COUNT query to get total records for pagination metadata
                StringBuilder countQuery = new StringBuilder(DashboardRepoQueryConstants.CHEMICAL_EXPORT_COUNT_QUERY);
                countQuery.append(fromQuery);

                // Check if the final query is not blank
                if(FwUtils.isNotBlankOrNullString(sbQuery.toString())){
                    // Execute the query to retrieve ExportsResponseDTO data
                    List<ExportsResponse> exportsList = jdbcTemplate.query(sbQuery.toString(), new Object[] { },
                            new BeanPropertyRowMapper<ExportsResponse>(ExportsResponse.class));

                    // Check if the exportsList is not null and not empty
                    if(FwUtils.isNotNullOrNotEmpty(exportsList)){
                        // Set the data and metadata in the searchResponse object
                        searchResponse.setData(exportsList);
                        searchResponse.setMetaData(
                                metadataRepo.getMetadata(gridOptions.getPaging(),
                                        DashboardRepoQueryConstants.getCountQuery(countQuery.toString())));
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // Return the populated GridSearchResponse object
        return searchResponse;
    }

    public GridSearchResponse<ImportsResponse> getCountryImportsData(GridOptions<DashboardDTO> gridOptions) {
        GridSearchResponse<ImportsResponse> searchResponse = new GridSearchResponse<>();
        try {
            // Check if gridOptions and its metadata are not null, and required fields are not blank
            if(Objects.nonNull(gridOptions) && Objects.nonNull(gridOptions.getMetadata())
                    && FwUtils.isNotBlankOrNullString(gridOptions.getMetadata().getExporterName()) && FwUtils.isNotBlankOrNullString(gridOptions.getMetadata().getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(gridOptions.getMetadata().getFromDate()) && FwUtils.isNotBlankOrNullString(gridOptions.getMetadata().getToDate())){
                // Build the SELECT part of the query
                StringBuilder sbQuery = new StringBuilder(DashboardRepoQueryConstants.CHEMICAL_IMPORT_SELECT_QUERY);
                // Build the FROM and WHERE part of the query using metadata and search filters
                StringBuilder fromQuery = new StringBuilder(DashboardRepoQueryConstants.getFromAndWhereQuery(gridOptions.getMetadata()));
                fromQuery.append(QueryUtils.getSearchFilter(gridOptions.getSearchFilters()));

                // Combine the SELECT, FROM, and WHERE parts of the query
                sbQuery.append(fromQuery);
                // GROUP BY QUERY
                sbQuery.append(DashboardRepoQueryConstants.CHEMICAL_IMPORT_GROUP_BY_QUERY);
                // Add sorting and pagination to the query
                sbQuery.append(QueryUtils.getSortOrder(gridOptions.getSortFilters()));
                sbQuery.append(QueryUtils.getLimitOffset(gridOptions.getPaging()));

                // Build the COUNT query to get total records for pagination metadata
                StringBuilder countQuery = new StringBuilder(DashboardRepoQueryConstants.CHEMICAL_IMPORT_COUNT_QUERY);
                countQuery.append(fromQuery);

                // Check if the final query is not blank
                if(FwUtils.isNotBlankOrNullString(sbQuery.toString())){
                    // Execute the query to retrieve ImportsResponseDTO data
                    List<ImportsResponse> importsList = jdbcTemplate.query(sbQuery.toString(), new Object[] { },
                            new BeanPropertyRowMapper<ImportsResponse>(ImportsResponse.class));

                    // Check if the importsList is not null and not empty
                    if(FwUtils.isNotNullOrNotEmpty(importsList)){
                        // Set the data and metadata in the searchResponse object
                        searchResponse.setData(importsList);
                        searchResponse.setMetaData(
                                metadataRepo.getMetadata(gridOptions.getPaging(),
                                        DashboardRepoQueryConstants.getCountQuery(countQuery.toString())));
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // Return the populated GridSearchResponse object
        return searchResponse;
    }

    public CountryWiseCount getCountryWiseCount(DashboardDTO dashboardDTO){
        CountryWiseCount countryWiseCount = null;
        try {
            // Check if the input DashboardDTO and its required fields are not null or blank
            if(Objects.nonNull(dashboardDTO)
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getExporterName()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(dashboardDTO.getFromDate()) && FwUtils.isNotBlankOrNullString(dashboardDTO.getToDate())){
                // Query to retrieve a list of CountryWiseCount objects based on the provided parameters
                List<CountryWiseCount> countList = jdbcTemplate.query(DashboardRepoQueryConstants.COUNTRY_WISE_TOTAL_COUNT, new Object[] {
                                dashboardDTO.getFromDate(), dashboardDTO.getToDate(),
                                dashboardDTO.getExporterName(), dashboardDTO.getImporterCountry()
                        },
                        new BeanPropertyRowMapper<CountryWiseCount>(CountryWiseCount.class));
                // Check if the countList is not null and not empty, and retrieve the first element
                if(FwUtils.isNotNullOrNotEmpty(countList) && Objects.nonNull(countList.get(0))){
                    countryWiseCount = countList.get(0);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // Return the populated CountryWiseCount object
        return countryWiseCount;
    }

    public GridSearchResponse<ChemicalResponse> getCountryChemicalData(GridOptions<DashboardDTO> gridOptions) {
        GridSearchResponse<ChemicalResponse> searchResponse = new GridSearchResponse<>();
        try {
            // Check if gridOptions and its metadata are not null, and required fields are not blank
            if(Objects.nonNull(gridOptions) && Objects.nonNull(gridOptions.getMetadata())
                    && FwUtils.isNotBlankOrNullString(gridOptions.getMetadata().getExporterName()) && FwUtils.isNotBlankOrNullString(gridOptions.getMetadata().getImporterCountry())
                    && FwUtils.isNotBlankOrNullString(gridOptions.getMetadata().getFromDate()) && FwUtils.isNotBlankOrNullString(gridOptions.getMetadata().getToDate())
                    && FwUtils.isNotBlankOrNullString(gridOptions.getMetadata().getChemicalName()) && FwUtils.hasId(gridOptions.getMetadata().getRitcCode())){
                // Build the SELECT part of the query
                StringBuilder sbQuery = new StringBuilder(DashboardRepoQueryConstants.COUNTRY_CHEMICAL_DATA_SELECT_QUERY);
                // Build the FROM and WHERE part of the query using metadata and search filters
                StringBuilder fromQuery = new StringBuilder(DashboardRepoQueryConstants.getFromAndWhereQuery(gridOptions.getMetadata()));
                fromQuery.append(QueryUtils.getSearchFilter(gridOptions.getSearchFilters()));

                // Combine the SELECT, FROM, and WHERE parts of the query
                sbQuery.append(fromQuery);
                // GROUP BY QUERY
                sbQuery.append(DashboardRepoQueryConstants.COUNTRY_CHEMICAL_DATA_GROUP_BY);
                // Add sorting and pagination to the query
                sbQuery.append(QueryUtils.getSortOrder(gridOptions.getSortFilters()));
                sbQuery.append(QueryUtils.getLimitOffset(gridOptions.getPaging()));

                // Build the COUNT query to get total records for pagination metadata
                StringBuilder countQuery = new StringBuilder(DashboardRepoQueryConstants.COUNTRY_CHEMICAL_DATA_COUNT_QUERY);
                countQuery.append(fromQuery);

                // Check if the final query is not blank
                if(FwUtils.isNotBlankOrNullString(sbQuery.toString())){
                    // Execute the query to retrieve ChemicalResponseDTO data
                    List<ChemicalResponse> importsList = jdbcTemplate.query(sbQuery.toString(), new Object[] { },
                            new BeanPropertyRowMapper<ChemicalResponse>(ChemicalResponse.class));
                    // Check if the importsList is not null and not empty
                    if(FwUtils.isNotNullOrNotEmpty(importsList)){
                        // Set the data and metadata in the searchResponse object
                        searchResponse.setData(importsList);
                        searchResponse.setMetaData(
                                metadataRepo.getMetadata(gridOptions.getPaging(),
                                        DashboardRepoQueryConstants.getCountQuery(countQuery.toString())));
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // Return the populated GridSearchResponse object
        return searchResponse;
    }

}
