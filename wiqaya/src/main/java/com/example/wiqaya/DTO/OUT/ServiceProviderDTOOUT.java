package com.example.wiqaya.DTO.OUT;

import com.example.wiqaya.Model.Offer;
import com.example.wiqaya.Model.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class ServiceProviderDTOOUT {

    private String name;

    private String email;

    private String phoneNumber;

    private String commercialRegistration;

    private Integer doneOrdersNum;

    private Double averageRating;

}
