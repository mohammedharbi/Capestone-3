package com.example.wiqaya.Controller;

import com.example.wiqaya.ApiResponse.ApiResponse;
import com.example.wiqaya.Model.RequestInspection;
import com.example.wiqaya.Service.RequestInspectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wiqaya/request-inspection")
@RequiredArgsConstructor
public class RequestInspectionController {

    private final RequestInspectionService requestInspectionService;

    @GetMapping("/get")
    public ResponseEntity getRequestInspections() {
        return ResponseEntity.status(200).body(requestInspectionService.getRequestInspection());
    }

    @PostMapping("/add")
    public ResponseEntity addRequestInspection(@RequestBody @Valid RequestInspection requestInspection) {
        requestInspectionService.addRequestInspection(requestInspection);
        return ResponseEntity.status(200).body(new ApiResponse("request inspection added"));
    }

//    @PutMapping("/update/{id} do we really need it ?
//    public ResponseEntity updateRequestInspection(@PathVariable Integer id, @RequestBody @Valid RequestInspection requestInspection) {
//       requestInspectionService.updateRequestInspection(id, requestInspection);
//       return ResponseEntity.status(200).body(new ApiResponse("request inspection updated"));
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRequestInspection(@PathVariable Integer id) {
        requestInspectionService.deleteRequestInspection(id);
        return ResponseEntity.status(200).body(new ApiResponse("request inspection deleted"));
    }

}
