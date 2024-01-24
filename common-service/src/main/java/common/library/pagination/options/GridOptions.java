package common.library.pagination.options;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 *
 * @author Santosh Wakhare
 *
 * */

@SuperBuilder
@NoArgsConstructor
@ToString
public class GridOptions<T> extends FwGridOptions<T> implements Serializable {
    private static final long serialVersionUID = 1L;

}
