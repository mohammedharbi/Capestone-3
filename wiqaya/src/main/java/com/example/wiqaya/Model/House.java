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
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // City
    @NotEmpty(message = "City cannot be empty")
    @Size(min = 5, max = 30, message = "City name must be between 5 and 30 characters")
    @Column(columnDefinition = "varchar(30) not null")
    private String city;

    // location
    @NotEmpty(message = "Location cannot be empty")
    @Size(max = 50, message = "Location cannot exceed 50 characters")
    @Column(columnDefinition = "varchar(50) not null")
    private String location;

    // condition Percentage
    @Min(value = 0, message = "Condition percentage must be at least 0")
    @Max(value = 100, message = "Condition percentage cannot exceed 100")
    @Column(columnDefinition = "int not null")
    private Integer conditionPercentage;

    // house type
    @NotEmpty(message = "Type cannot be empty")
    @Pattern(regexp = "^(Villa|Apartment|Duplex|Floor|Traditional House)$", message = "Type must be one of the following: Villa, Apartment, Duplex, Floor, or Traditional House")
    @Column(columnDefinition = "varchar(30) not null")
    private String type;

    // status
    //    un checked                 // Initial state when house is added
    //    pending_inspection, // Waiting for engineer inspection
    //    Reported,          // Engineer completed inspection and report is available
    //    offers_pending,     // Reviewing safety company offers
    //    offer_accepted,     // Chosen safety company starts work
    //    Completed           // Safety work completed
    @Pattern(regexp = "^(un checked|pending_inspection|inspected|offers_pending|offer_accepted|completed)$", message = "Status must be one of the following: new, pending_inspection, inspected, offers_pending, offer_accepted, or completed")
    @Column(columnDefinition = "varchar(20) not null")
    private String status; // Default value


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "house")
    private Set<RequestInspection> requestInspections;

    @OneToMany(cascade = CascadeType.ALL,mappedBy ="house" )
    private Set<Report> reports;

}
