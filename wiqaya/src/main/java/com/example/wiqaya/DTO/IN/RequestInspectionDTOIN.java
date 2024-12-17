package com.example.wiqaya.DTO.IN;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RequestInspectionDTOIN {

    private Integer house_id;

    @FutureOrPresent
    private LocalDate date;
}
