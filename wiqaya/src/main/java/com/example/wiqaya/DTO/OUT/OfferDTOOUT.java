package com.example.wiqaya.DTO.OUT;

import com.example.wiqaya.Model.Report;
import com.example.wiqaya.Model.ServiceProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OfferDTOOUT {


    private Integer id;

    private String description;

    private String status;

    private Double price;

    private Integer serviceprovider_id;
}
