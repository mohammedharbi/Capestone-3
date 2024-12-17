package com.example.wiqaya.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

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


    @Column(columnDefinition = "boolean not null")
    private Boolean structuralElements;


    @Column(columnDefinition = "boolean not null")
    private Boolean fireDetection;


    @Column(columnDefinition = "boolean not null")
    private Boolean  electricalDange;


    @Column(columnDefinition = "boolean not null")
    private Boolean  heatingCookingSystems;


    @Column(columnDefinition = "boolean not null")
    private Boolean  emergencyPreparedness;


    @Column(columnDefinition = "boolean not null")
    private Boolean  hazardousMaterialsMtorage;


    @Column(columnDefinition = "boolean not null")
    private Boolean  ventilationSmokeManagement;


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

    @ManyToOne
    @JoinColumn(name="house_id",referencedColumnName = "id")
    @JsonIgnore
    private House house;


//    @OneToMany(cascade = CascadeType.ALL , mappedBy = "report")
//    private Set<Offer> requestInspections;


    @OneToOne
    @MapsId
    @JsonIgnore
    private RequestInspection requestInspection;



}
