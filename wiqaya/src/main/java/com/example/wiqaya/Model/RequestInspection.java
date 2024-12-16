package com.example.wiqaya.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RequestInspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@CreationTimestamp maybe we will use it ?
    @Column(nullable = false, updatable = false)
    private LocalDate date;

    @Pattern(regexp = "^(Reported|Pending)$", message = "Request inspection status must be either 'Reported' or 'Pending'")
    @Column(columnDefinition = "varchar(8) not null")
    private String status = "Pending"; // it will be 'Pending' by default therefore no need for @NotEmpty. the Engineer will be assigned and accept the request, after checking the Home condition the status can be changed to 'Reported'

//    @ManyToOne
//    @JoinColumn(name = "engineer_id", referencedColumnName = "id")
//    @JsonIgnore
//    private Engineer engineer;
}
