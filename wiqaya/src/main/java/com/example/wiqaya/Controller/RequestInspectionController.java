package com.example.wiqaya.Controller;

import com.example.wiqaya.ApiResponse.ApiResponse;
import com.example.wiqaya.DTO.IN.RequestInspectionDTOIN;
import com.example.wiqaya.Model.RequestInspection;
import com.example.wiqaya.Service.RequestInspectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/wiqaya/request-inspection")
@RequiredArgsConstructor
public class RequestInspectionController {

    private final RequestInspectionService requestInspectionService;

    @GetMapping("/get/admin-id/{id}")
    public ResponseEntity getRequestInspections(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(requestInspectionService.getRequestInspection(id));
    }

    @PostMapping("/add/user_id/{user_id}/house_id/{house_id}")
    public ResponseEntity addRequestInspection(@PathVariable Integer user_id,@PathVariable Integer house_id,@RequestBody @Valid RequestInspectionDTOIN requestInspectionDTOIN) {
        requestInspectionService.addRequestInspection(user_id,house_id,requestInspectionDTOIN);
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

    @GetMapping("/get-available-engineer/date/{date}")
    public ResponseEntity getAvailableEngineerDate(@PathVariable LocalDate date) {
        return ResponseEntity.status(200).body(requestInspectionService.getAvailableEngineersForDate(date));
    }

    @PutMapping("assign-eng/adminId/{adminId}/engId/{engId}/requestInspectionId/{requestInspectionId}")
    public ResponseEntity assignEng(@PathVariable Integer adminId,@PathVariable Integer engId,@PathVariable Integer requestInspectionId) {
        requestInspectionService.assignEng(adminId,engId,requestInspectionId);
        return ResponseEntity.status(200).body(new ApiResponse("request inspection assigned to an engineer"));
    }

    @PutMapping("/cancel-request/userid/{userid}/requestid/{requestid}")
    public ResponseEntity CancelRequestInspection(@PathVariable Integer userid,@PathVariable Integer requestid){
      requestInspectionService.CancelRequestInspection(userid,requestid);
      return ResponseEntity.status(200).body(new ApiResponse("Request Inspection Cancelled"));
    }


}
