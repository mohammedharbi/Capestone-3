package com.example.wiqaya.DTO.OUT;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HouseDTOOUT2 {

    // City
    private String city;

    // location
    private String location;

    // house type
    private String type;

    // status of the house .. default "un_checked"
    private String status;

    // condition Percentage
    private Integer conditionPercentage;

}
