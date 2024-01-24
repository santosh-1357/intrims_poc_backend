package common.library.pagination.sorting;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 *
 * @author Santosh Wakhare
 *
 * */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class Sorting implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sortBy;
    private int sortOrder;

}
