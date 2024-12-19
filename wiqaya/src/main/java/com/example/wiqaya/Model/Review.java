package com.example.wiqaya.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
//Sara
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
    @Column(columnDefinition = "int not null")
    private Double rating;

    @NotEmpty(message = "Comment cannot be empty")
//    @Max(value = 50 , message = "comment length can't be more than 50 characters")
    @Column(columnDefinition = "varchar(50) not null")
    private String comment;

    //relation

    // many reviews for one user
    @ManyToOne
    private User user;


    @ManyToOne
    @JoinColumn(name = "serviceprovider_id", referencedColumnName = "id")
    @JsonIgnore
    private ServiceProvider serviceProvider;

}