package com.example.wiqaya.DTO.OUT;

import com.example.wiqaya.Model.Report;
import com.example.wiqaya.Model.RequestInspection;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class EngineerDTOOUT {

    private String fullName;

    private String phoneNumber;

    private String status;

    private Boolean availability;
}