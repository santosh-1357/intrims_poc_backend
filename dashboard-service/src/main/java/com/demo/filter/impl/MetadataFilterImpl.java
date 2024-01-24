package com.demo.filter.impl;

import com.demo.util.constants.Constants;
import common.library.pagination.options.GridOptions;
import common.library.pagination.search.MetadataFilter;
import org.springframework.stereotype.Component;

@Component
public class MetadataFilterImpl implements MetadataFilter {

    @Override
    public String doFilter(String key) throws Exception { return key; }

    @Override
    public MetadataFilter getFilterObject(String className) throws Exception {
        switch (className){
            case Constants.DashboardDTO:
                return new DashboardFilter();
            default:
                throw  new Exception("Invalid Filter for className : "+ className);
        }
    }

    @Override
    public <T> GridOptions<T> filterMetadata(GridOptions<T> gridOptions) {
        return MetadataFilter.super.filterMetadata(gridOptions);
    }
}
