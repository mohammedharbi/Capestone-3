package com.example.wiqaya.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDate;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @AssertTrue
    @Column(columnDefinition = "boolean not null")
    private Boolean structuralElements;

    @AssertTrue
    @Column(columnDefinition = "boolean not null")
    private Boolean fireDetection;

    @AssertTrue
    @Column(columnDefinition = "boolean not null")
    private Boolean  electricalDange;

    @AssertTrue
    @Column(columnDefinition = "boolean not null")
    private Boolean  heatingCookingSystems;

    @AssertTrue
    @Column(columnDefinition = "boolean not null")
    private Boolean  emergencyPreparedness;

    @AssertTrue
    @Column(columnDefinition = "boolean not null")
    private Boolean  hazardousMaterialsMtorage;

    @AssertTrue
    @Column(columnDefinition = "boolean not null")
    private Boolean  ventilationSmokeManagement;

    @AssertTrue
    @Column(columnDefinition = "boolean not null")
    private Boolean  exteriorSurroundings;

    @PositiveOrZero(message = "percentage should be positive")
    @Min(0)
    @Max(100)
    @Column(columnDefinition = "int not null")
    private  Integer percentage;

    @Column(columnDefinition = "varchar(200)")
    private String notes;

    @Column(columnDefinition = "varchar(200)")
    private String requiredItems;


    @Column(columnDefinition = "timestamp not null default current_timestamp")
    private LocalDate ReportedDate;

    @ManyToOne
    @JoinColumn(name="engineer_id",referencedColumnName = "id")
    @JsonIgnore
    private Engineer engineer;



}
