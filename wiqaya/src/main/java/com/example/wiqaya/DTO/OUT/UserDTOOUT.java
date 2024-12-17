package com.example.wiqaya.DTO.OUT;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOOUT {

    // name
    private String name;

    // email
    private String email;

    // phoneNumber
    private String phoneNumber;

    // username
    private String username;

    // password
    private String password;

}
