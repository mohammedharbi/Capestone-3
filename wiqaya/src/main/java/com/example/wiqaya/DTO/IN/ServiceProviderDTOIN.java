package com.example.wiqaya.DTO.IN;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServiceProviderDTOIN {

    // name
    @NotEmpty(message = "Name is required")
    @Size(min = 4, max = 30 , message = "name must be between 5 and 30 characters")
    private String name;

    // email
    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email format")
    private String email;

    // phoneNumber
    @NotEmpty(message = "phone number is empty")
    @Pattern(regexp = "^(\\+966|0)?5\\d{8}$",   message = "Phone number must start with +966 or 05 and be followed by 8 digits")
    private String phoneNumber;

    @Pattern(regexp = "^[0-9]{10}$", message = "Commercial Registration must consist of exactly 10 digits")
    private String commercialRegistration;


}
