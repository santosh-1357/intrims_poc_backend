package common.library.pagination.options;

import common.library.pagination.paging.Paging;
import common.library.pagination.paging.PagingAndSorting;
import common.library.pagination.search.SearchFilter;
import common.library.pagination.sorting.Sorting;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Santosh Wakhare
 *
 * */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class FwGridOptions<T>  implements Serializable {
    private static final long serialVersionUID = 1L;

    private T metadata;
    private Paging paging;
    private Sorting sorting;
    private List<Sorting> sortFilters;
    private PagingAndSorting pagingAndSorting;
    private List<SearchFilter> searchFilters;

}
