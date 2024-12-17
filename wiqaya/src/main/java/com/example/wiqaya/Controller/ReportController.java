package com.example.wiqaya.Controller;

import com.example.wiqaya.ApiResponse.ApiResponse;
import com.example.wiqaya.DTO.IN.ReportDTOIN;
import com.example.wiqaya.Repository.ReportRepository;
import com.example.wiqaya.Service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController {
private final ReportService reportService;


    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body( reportService.getAll());
    }

    @PostMapping("/add/engineerId/{engineerId}")
    public ResponseEntity add( @PathVariable Integer engineerId,@RequestBody @Valid ReportDTOIN reportDTOIN){
        reportService.add(engineerId,reportDTOIN);
        return ResponseEntity.status(200).body(new ApiResponse("report created successfully"));
    }
}
