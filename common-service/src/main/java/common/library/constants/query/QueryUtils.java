package common.library.constants.query;


import common.library.constants.app.FwUtils;
import common.library.pagination.paging.Paging;
import common.library.pagination.paging.PagingAndSorting;
import common.library.pagination.search.SearchFilter;
import common.library.pagination.sorting.Sorting;

import java.util.List;
import java.util.Objects;

public final class QueryUtils implements QueryConstants {

    public static String getSortOrder(PagingAndSorting sorting) {
        if(Objects.nonNull(sorting)
                && FwUtils.isNotBlankOrNullString(sorting.getSortBy())
                && FwUtils.isNotBlankOrNullString(String.valueOf(sorting.getSortOrder()))){
            return ORDER_BY + sorting.getSortBy() + (sorting.getSortOrder() == -1 ? DESCENDING : ASCENDING );
        }
        return "";
    }

    public static String getSortOrder(Sorting sorting) {
        if(Objects.nonNull(sorting)
                && FwUtils.isNotBlankOrNullString(sorting.getSortBy())
                && FwUtils.isNotBlankOrNullString(String.valueOf(sorting.getSortOrder()))){
            return ORDER_BY + sorting.getSortBy() + (sorting.getSortOrder() == -1 ? DESCENDING : ASCENDING );
        }
        return "";
    }

    public static String getSortOrder(List<Sorting> sorting) {
        StringBuilder stringBuilder = new StringBuilder();
        try{
            if(Objects.nonNull(sorting) && FwUtils.isNotNullOrNotEmpty(sorting)){
                stringBuilder.append(ORDER_BY);
                sorting.forEach(
                        sort -> {
                            if(FwUtils.isNotBlankOrNullString(sort.getSortBy())
                                    && FwUtils.isNotBlankOrNullString(String.valueOf(sort.getSortOrder()))){
                                stringBuilder.append(sort.getSortBy());
                                stringBuilder.append((sort.getSortOrder() == -1 ? DESCENDING : ASCENDING ));
                                stringBuilder.append(" , ");
                            }

                        }
                );
                stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String getLimitOffset(PagingAndSorting paging) {
        if(Objects.nonNull(paging)
                && FwUtils.isNotBlankOrNullString(String.valueOf(paging.getPageSize()))
                && FwUtils.isNotBlankOrNullString(String.valueOf(paging.getLowerLimit())) ){
            return LIMIT + paging.getPageSize() + OFFSET + paging.getLowerLimit() + " ";
        }
        return "";
    }

    public static String getLimitOffset(Paging page) {
        if(Objects.nonNull(page)
                && FwUtils.isNotBlankOrNullString(String.valueOf(page.getPageSize()))
                && FwUtils.isNotBlankOrNullString(String.valueOf(page.getLowerLimit())) ){
            return LIMIT + page.getPageSize() + OFFSET + page.getLowerLimit() + " ";
        }
        return "";
    }

    public  static  String getSearchFilter(List<SearchFilter> searchFilterList){
        StringBuilder sbQuery = new StringBuilder();
        try{
            if(FwUtils.isNotNullOrNotEmpty(searchFilterList)){
                searchFilterList.forEach(
                        search -> {
                            if(FwUtils.isNotBlankOrNullString(search.getKey())
                                    && FwUtils.isNotBlankOrNullString(search.getValue())){
                                sbQuery.append(AND + search.getKey() + LIKE);
                                sbQuery.append("'" + WILD_CARD_STRING + search.getValue() + WILD_CARD_STRING + "'");
                            }
                        }
                );
            }
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return  sbQuery.toString();
    }

}
