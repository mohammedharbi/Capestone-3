package com.example.wiqaya.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//mohammed
public class Engineer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "full name is empty")
    @Size(min = 5, max = 30, message = "full name must be between 5 and 30 characters")
    @Column(columnDefinition = "varchar(30) not null")
    private String fullName;

    @NotEmpty(message = "phone number is empty")
    @Pattern(regexp = "^(\\+966|0)?5\\d{8}$")
    @Column(columnDefinition = "varchar(13) not null unique")
    private String phoneNumber;

    @NotEmpty(message = "email is empty")
    @Email(message = "Email must be a valid email")
    @Size(max = 40, message = "Email must be max 40 characters")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;

    @NotEmpty(message = "city is empty")
    @Pattern(regexp = "^(Riyadh)$", message = "in order to be registered the engineer must be in 'Riyadh' city")
    @Column(columnDefinition = "varchar(6) not null")
    private String city;

    @NotEmpty(message = "accreditation number is empty")
    @Pattern(regexp = "^SCE-\\d{4}\\d{6}$", message = "accreditation number must match the following pattern 'SCE-YYYYNNNNNN'")
    @Column(columnDefinition = "varchar(14) not null unique")
    private String accreditationNumber; // for example SCE-2024567890

    @Pattern(regexp = "^(UnderReview|Approved|Rejected)$", message = "Engineer status must be either UnderReview | Approved | Rejected")
    @Column(columnDefinition = "varchar(20)")
    private String status; // it will be Inactive by default therefore no need for @NotEmpty. the admin  will check the Engineer accreditation number then decide to active it or not

    @Column(nullable = true)
    private String rejectionReason;

//    @Column(columnDefinition = "int not null")
//    private Integer reportedHousesNum;

    @Column(columnDefinition = "varchar(11) ")
    private Boolean availability = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "engineer")
    @JsonIgnore
    private Set<RequestInspection> requestInspections;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "engineer")
    private Set<Report> reports;

}
