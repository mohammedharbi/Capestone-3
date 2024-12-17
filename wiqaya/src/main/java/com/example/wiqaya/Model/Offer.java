package com.example.wiqaya.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "description is empty")
    @Size(min = 5, max = 100, message = "description must be between 5 and 100 characters")
    @Column(columnDefinition = "varchar(100)")
    private String description;

    @Pattern(regexp = "^(Accepted|Rejected|Pending)$")
    @Column(columnDefinition = "varchar(8)")
    private String status= "Pending";

    @NotNull(message = "price is null")
    @Positive
    @Column(columnDefinition = "double not null")
    private Double price;

//    @ManyToOne

//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "serviceprovider_id", referencedColumnName = "id")
//    private ServiceProvider serviceProvider;
}
