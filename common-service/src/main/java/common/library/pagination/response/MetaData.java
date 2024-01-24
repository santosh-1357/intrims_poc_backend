package common.library.pagination.response;

import lombok.*;

/**
 *
 * @author Santosh Wakhare
 *
 * */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetaData {
    private int pageNumber;
    private int pageSize;
    private int totalRecords;
    private int totalPages;

}
