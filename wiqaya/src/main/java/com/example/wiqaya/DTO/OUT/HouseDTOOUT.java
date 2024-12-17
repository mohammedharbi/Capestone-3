package com.example.wiqaya.DTO.OUT;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HouseDTOOUT {

    // City
    private String city;

    // location
    private String location;

    // condition Percentage
    private Integer conditionPercentage;

    // house type
    private String type;

    private String status;

}
