package com.example.wiqaya.DTO.IN;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseDTOIN {

    // City
    @NotEmpty(message = "City cannot be empty")
    @Size(min = 5, max = 30, message = "City name must be between 5 and 30 characters")
    private String city;

    // location
    @NotEmpty(message = "Location cannot be empty")
    @Size(max = 50, message = "Location cannot exceed 50 characters")
    private String location;

    // condition Percentage
    @Min(value = 0, message = "Condition percentage must be at least 0")
    @Max(value = 100, message = "Condition percentage cannot exceed 100")
    private Integer conditionPercentage;

    // house type
    @NotEmpty(message = "Type cannot be empty")
    @Pattern(regexp = "^(Villa|Apartment|Duplex|Floor|Traditional House)$", message = "Type must be one of the following: Villa, Apartment, Duplex, Floor, or Traditional House")
    private String type;

    // status
    //    un checked                 // Initial state when house is added
    //    pending_inspection, // Waiting for engineer inspection
    //    Reported,          // Engineer completed inspection and report is available
    //    offers_pending,     // Reviewing safety company offers
    //    offer_accepted,     // Chosen safety company starts work
    //    Completed           // Safety work completed
    @Pattern(regexp = "^(new|pending_inspection|inspected|offers_pending|offer_accepted|completed)$", message = "Status must be one of the following: new, pending_inspection, inspected, offers_pending, offer_accepted, or completed")
    private String status; // Default value

}
