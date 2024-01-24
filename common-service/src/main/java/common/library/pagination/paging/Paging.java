package common.library.pagination.paging;

import common.library.constants.app.FwConstants;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Paging implements Serializable {
    private static final long serialVersionUID = 1L;

    private int pageNumber;
    private int pageSize;

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
