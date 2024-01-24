package common.library.pagination.paging;

import common.library.constants.app.FwConstants;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
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
public class PagingAndSorting implements Serializable {
    private static final long serialVersionUID = 1L;

    private int pageNumber;
    private int pageSize;
    private int sortOrder;
    private String sortBy;

    public int getLowerLimit() {
        if (getPageNumber() > 0) {
            return ((getPageNumber() * getPageSize() - getPageSize()));
        }
        return 0;
    }

    public int getUpperLimit() {
        if (getPageNumber() > 0) {
            return (getPageNumber() * getPageSize() - 1);
        }
        return FwConstants.DEFAULT_PAGE_SIZE;
    }

}
