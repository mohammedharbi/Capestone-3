package com.example.wiqaya.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
//Hadeel
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "description is empty")
    @Size(min = 5, max = 100, message = "description must be between 5 and 100 characters")
    @Column(columnDefinition = "varchar(100)")
    private String description;



    @NotNull(message = "price is null")
    @Positive
    @Column(columnDefinition = "double not null")
    private Double price;
    @Pattern(regexp = "^(Accepted|Rejected|Pending)$")
    @Column(columnDefinition = "varchar(8)")
    private String status= "Pending";

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "serviceprovider_id", referencedColumnName = "id")
    private ServiceProvider serviceProvider;

    // here  ,, many offers for one report
    @ManyToOne
    private Report report;


}
