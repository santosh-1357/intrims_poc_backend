package com.demo.domian.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CountDTO {

    private int id;
    private  String name;
    private  int quantity;
}
