package com.example.wiqaya.DTO.OUT;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDTOOUT {

    private Integer rating;

    private String comment;

    private String username;
}