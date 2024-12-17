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

    // house type
    @NotEmpty(message = "Type cannot be empty")
    @Pattern(regexp = "^(Villa|Apartment|Duplex|Floor|Traditional House)$", message = "Type must be one of the following: Villa, Apartment, Duplex, Floor, or Traditional House")
    private String type;


}
