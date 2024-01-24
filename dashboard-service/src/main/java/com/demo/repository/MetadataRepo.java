package com.demo.repository;

import common.library.constants.app.FwUtils;
import common.library.pagination.paging.Paging;
import common.library.pagination.paging.PagingAndSorting;
import common.library.pagination.response.MetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class MetadataRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public MetaData getMetadata(PagingAndSorting paging, String countQuery){
        MetaData metaData = new MetaData();
        try {
            // Check if the countQuery and paging parameters are not null or blank
            if(FwUtils.isNotBlankOrNullString(countQuery) && Objects.nonNull(paging)
                    && FwUtils.isNotBlankOrNullString(String.valueOf(paging.getPageNumber()))
                    && FwUtils.isNotBlankOrNullString(String.valueOf(paging.getPageSize()))){
                // Execute a database query to retrieve metadata information
                List<MetaData> metaDataList = jdbcTemplate.query(countQuery, new Object[] { },
                        new BeanPropertyRowMapper<MetaData>(MetaData.class));
                // Check if the metaDataList is not null and not empty
                if(FwUtils.isNotNullOrNotEmpty(metaDataList)){

                    // Retrieve total records from the first element of the list
                    int totalRecords = metaDataList.get(0).getTotalRecords();
                    // Calculate total pages based on total records and page size
                    int totalPages = (int) Math.ceil(totalRecords / (double) paging.getPageSize());

                    // Set metadata properties in the MetaData object
                    metaData.setPageNumber(paging.getPageNumber());
                    metaData.setPageSize(paging.getPageSize());
                    metaData.setTotalRecords(totalRecords);
                    metaData.setTotalPages(totalPages);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // Return the MetaData object containing pagination information
        return  metaData;
    }

    public MetaData getMetadata(Paging paging, String countQuery){
        MetaData metaData = new MetaData();
        try {
            // Check if the countQuery and paging parameters are not null or blank
            if(FwUtils.isNotBlankOrNullString(countQuery) && Objects.nonNull(paging)
                    && FwUtils.isNotBlankOrNullString(String.valueOf(paging.getPageNumber()))
                    && FwUtils.isNotBlankOrNullString(String.valueOf(paging.getPageSize()))){
                // Execute a database query to retrieve metadata information
                List<MetaData> metaDataList = jdbcTemplate.query(countQuery, new Object[] { },
                        new BeanPropertyRowMapper<MetaData>(MetaData.class));
                // Check if the metaDataList is not null and not empty
                if(FwUtils.isNotNullOrNotEmpty(metaDataList)){

                    // Retrieve total records from the first element of the list
                    int totalRecords = metaDataList.get(0).getTotalRecords();
                    // Calculate total pages based on total records and page size
                    int totalPages = (int) Math.ceil(totalRecords / (double) paging.getPageSize());

                    // Set metadata properties in the MetaData object
                    metaData.setPageNumber(paging.getPageNumber());
                    metaData.setPageSize(paging.getPageSize());
                    metaData.setTotalRecords(totalRecords);
                    metaData.setTotalPages(totalPages);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // Return the MetaData object containing pagination information
        return  metaData;
    }
}
