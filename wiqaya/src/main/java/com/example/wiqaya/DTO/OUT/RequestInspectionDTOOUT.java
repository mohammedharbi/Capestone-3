package com.example.wiqaya.DTO.OUT;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RequestInspectionDTOOUT {

    private Integer id;

    private LocalDate date;

    private String status;

    private Integer house_id;

    private String houseStatus;

    private String houseType;

    private String city;

    private String engName;

    private String engPhoneNumber;
}
