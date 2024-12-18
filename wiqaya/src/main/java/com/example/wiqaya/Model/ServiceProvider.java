package com.example.wiqaya.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ServiceProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(50) not null")
   private String name;

   @Column(columnDefinition = "varchar(100) not null")
private String email;

   @Column(columnDefinition = "varchar(13) not null unique")
private String phoneNumber;

    @Column(columnDefinition = "varchar(10) not null")
    private String commercialRegistration;

   @Pattern(regexp = "^(UnderReview|Approved|Rejected)$", message = "Service provider status must be either 'Active' or 'Inactive'")
    @Column(columnDefinition = "varchar(11) ")
private String status;

    @Column(nullable = true)
    private String rejectionReason;

 @Column(columnDefinition = "int not null")
private Integer doneOrdersNum;

 @Column(columnDefinition = "decimal not null")
private Double averageRating;

    @OneToMany(cascade = CascadeType.ALL,mappedBy ="serviceProvider" )
    private Set<Offer> offers;

    @OneToMany(cascade = CascadeType.ALL,mappedBy ="serviceProvider" )
    private Set<Review> reviews;

}
