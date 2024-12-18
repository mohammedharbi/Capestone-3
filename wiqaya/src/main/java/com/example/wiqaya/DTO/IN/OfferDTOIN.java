package com.example.wiqaya.DTO.IN;

import com.example.wiqaya.Model.ServiceProvider;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OfferDTOIN {

    @NotEmpty(message = "description is empty")
    @Size(min = 5, max = 100, message = "description must be between 5 and 100 characters")
    private String description;

    @NotNull(message = "price is null")
    @Positive
    private Double price;

}
