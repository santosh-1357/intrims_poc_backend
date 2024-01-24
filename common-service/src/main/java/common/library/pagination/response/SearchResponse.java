package common.library.pagination.response;

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
public class SearchResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<T> data;
    private MetaData metaData;

}
