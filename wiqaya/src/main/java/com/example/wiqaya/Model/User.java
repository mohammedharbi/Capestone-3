package com.example.wiqaya.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    // id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // name
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    // email
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    // phoneNumber
    @Column(columnDefinition = "varchar(13) not null unique")
    private String phoneNumber;

    // username
    @Column(columnDefinition = "varchar(30) not null unique")
    private String username;

    // password
    @Column(columnDefinition = "varchar(40) not null")
    private String password;

    // Role
    // default role is 'user'
    @Column(columnDefinition = "varchar(20) not null default 'user'")
    private String role ;

    // relation:

    // one User can have Many House "Set of houses"
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "user")
    private Set<House> houses;

//     one User can have many Review "Set of reviews"
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "user")
    private Set<Review> reviews;

}
