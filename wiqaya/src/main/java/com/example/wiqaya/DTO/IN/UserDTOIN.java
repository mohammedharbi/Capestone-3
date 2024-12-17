package com.example.wiqaya.DTO.IN;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTOIN {

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

    // username
    @NotEmpty(message = "username is empty")
    @Size(max = 30 ,message = "username can't be more than 30 characters")
    private String username;

    // password
    @NotEmpty(message = "password can't be empty")
    @Pattern( regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password must be at least 8 characters long and include at least one letter and one number")
    private String password;

}
