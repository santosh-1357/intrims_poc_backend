package common.library.pagination.search;

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
public class SearchFilter implements Serializable {
    private static final long serialVersionUID = 1L;

    private String key;
    private String value;

}
