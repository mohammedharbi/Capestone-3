package com.example.wiqaya.DTO.IN;


import com.example.wiqaya.Model.Engineer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class ReportDTOIN {


    private Boolean structuralElements;

    private Boolean fireDetection;

    private Boolean  electricalDange;


    private Boolean  heatingCookingSystems;


    private Boolean  emergencyPreparedness;


    private Boolean  hazardousMaterialsStorage;


    private Boolean  ventilationSmokeManagement;


    private Boolean  exteriorSurroundings;

    private String notes;

    private String requiredItems;



}
