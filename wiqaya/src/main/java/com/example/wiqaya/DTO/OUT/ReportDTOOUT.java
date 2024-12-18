package com.example.wiqaya.DTO.OUT;

import com.example.wiqaya.Model.Engineer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ReportDTOOUT {

    private Integer id;
    private Integer engineerId;

    private Boolean structuralElements;
    private Boolean fireDetection;

    private Boolean  heatingCookingSystems;

    private Boolean  emergencyPreparedness;

    private Boolean  ventilationSmokeManagement;

    private Boolean  exteriorSurroundings;

    private  Integer percentage;

    private String notes;

    private String requiredItems;

    private LocalDate ReportedDate;

    private String houseCity;
}
