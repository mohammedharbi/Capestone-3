package com.example.wiqaya.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
//sara
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // City
    @Column(columnDefinition = "varchar(30) not null")
    private String city;

    // location
    @Column(columnDefinition = "varchar(50) not null")
    private String location;

    // condition Percentage
    @Min(value = 0, message = "Condition percentage must be at least 0")
    @Max(value = 100, message = "Condition percentage cannot exceed 100")
    @Column(columnDefinition = "int not null")
    private Integer conditionPercentage;

    // house type
    @Column(columnDefinition = "varchar(30) not null")
    private String type;

    // status
    //    un checked                 // Initial state when house is added
    //    pending_inspection, // Waiting for engineer inspection
    //    Reported,          // Engineer completed inspection and report is available
    //    offers_pending,     // Reviewing safety company offers
    //    offer_accepted,     // Chosen safety company starts work
    //    Completed           // Safety work completed
    @Pattern(regexp = "^(un_checked|pending_inspection|checked|completed)$", message = "Status must be one of the following: un_checked, pending_inspection, inspected, offers_pending, offer_accepted, or completed")
    @Column(columnDefinition = "varchar(20) not null")
    private String status; // Default value "un checked"

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "house")
    @JsonIgnore
    private Set<RequestInspection> requestInspections;

    @OneToMany(cascade = CascadeType.ALL,mappedBy ="house" )
    private Set<Report> reports;

}
