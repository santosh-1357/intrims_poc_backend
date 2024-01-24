package common.library.pagination.search;


import common.library.constants.app.FwUtils;
import common.library.pagination.options.GridOptions;
import common.library.pagination.paging.PagingAndSorting;
import common.library.pagination.sorting.Sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface MetadataFilter {

    String doFilter(String key) throws Exception;

    MetadataFilter getFilterObject(String className) throws Exception;

    public default <T> GridOptions<T> filterMetadata(GridOptions<T> gridOptions){
        Sorting sorting = null;
        List<Sorting> sortingList = null;
        PagingAndSorting pagingAndSorting = null;
        List<SearchFilter> searchFilters = null;

        try{
            if(Objects.nonNull(gridOptions) && Objects.nonNull(gridOptions.getMetadata())){
                String className = gridOptions.getMetadata().getClass().getSimpleName();
                MetadataFilter filter = getFilterObject(className);

                if(Objects.nonNull(gridOptions.getSorting())
                        && FwUtils.isNotBlankOrNullString(gridOptions.getSorting().getSortBy())){
                    sorting = Sorting.builder()
                            .sortBy(filter.doFilter(gridOptions.getSorting().getSortBy()))
                            .sortOrder(gridOptions.getSorting().getSortOrder()).build();
                }

                if( Objects.nonNull(gridOptions.getPagingAndSorting())
                        && FwUtils.isNotBlankOrNullString(gridOptions.getPagingAndSorting().getSortBy())){

                    pagingAndSorting = PagingAndSorting.builder()
                            .pageNumber(gridOptions.getPagingAndSorting().getPageNumber())
                            .pageSize(gridOptions.getPagingAndSorting().getPageSize())
                            .sortBy(filter.doFilter(gridOptions.getPagingAndSorting().getSortBy()))
                            .sortOrder(gridOptions.getPagingAndSorting().getSortOrder())
                            .build();
                }

                if(Objects.nonNull(gridOptions.getSortFilters())
                        && FwUtils.isNotNullOrNotEmpty(gridOptions.getSortFilters())){
                    sortingList = new ArrayList<>();
                    for(Sorting sort : gridOptions.getSortFilters()){
                        if(FwUtils.isNotBlankOrNullString(sort.getSortBy())){
                            sortingList.add(Sorting.builder()
                                    .sortBy(filter.doFilter(sort.getSortBy()))
                                    .sortOrder(sort.getSortOrder())
                                    .build());
                        }
                    }
                }

                if(Objects.nonNull(gridOptions.getSearchFilters())
                        && FwUtils.isNotNullOrNotEmpty(gridOptions.getSearchFilters())){
                    searchFilters = new ArrayList<>();
                    for(SearchFilter search : gridOptions.getSearchFilters()){
                        if(FwUtils.isNotBlankOrNullString(search.getKey())
                                && FwUtils.isNotBlankOrNullString(search.getValue())){
                            searchFilters.add(SearchFilter.builder()
                                    .key(filter.doFilter(search.getKey()))
                                    .value(search.getValue())
                                    .build());
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        gridOptions.setSorting(sorting);
        gridOptions.setSortFilters(sortingList);
        gridOptions.setPagingAndSorting(pagingAndSorting);
        gridOptions.setSearchFilters(searchFilters);
        return  gridOptions;
    }

}
