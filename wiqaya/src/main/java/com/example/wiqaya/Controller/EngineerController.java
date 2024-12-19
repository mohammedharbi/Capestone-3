package com.example.wiqaya.Controller;

import com.example.wiqaya.ApiResponse.ApiResponse;
import com.example.wiqaya.Model.Engineer;
import com.example.wiqaya.Service.EngineerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wiqaya/engineer")
@RequiredArgsConstructor
public class EngineerController {

    private final EngineerService engineerService;

    @GetMapping("/get")
    public ResponseEntity getEngineers(){
        return ResponseEntity.status(200).body(engineerService.getAllEngineers());
    }

    @PostMapping("/add")
    public ResponseEntity addEngineer(@RequestBody @Valid Engineer engineer){
        engineerService.addEngineer(engineer);
        return ResponseEntity.status(200).body(new ApiResponse("Engineer added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEngineer(@PathVariable Integer id, @RequestBody @Valid Engineer engineer){
        engineerService.updateEngineer(id,engineer);
        return ResponseEntity.status(200).body(new ApiResponse("Engineer updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEngineer(@PathVariable Integer id){
        engineerService.deleteEngineer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Engineer deleted"));
    }

    //----------------------------------------

    // Endpoint No.5
    // Eng check on his status if he get approved or rejected
    @GetMapping("/check-eng-status/eng-id/{id}")
    public ResponseEntity<?> checkMyStatus(@PathVariable Integer id){
      return ResponseEntity.status(200).body(engineerService.checkMyStatus(id));
    }


    // Endpoint No.6
    // Eng get all the request Inspection that assign to him
    @GetMapping("/get-eng-request-by-id/{id}")
    public ResponseEntity<?> getAllRequestInspectionByEngId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(engineerService.getAllRequestInspectionByEngId(id));
    }

    // Endpoint No.30
    // get Eng reported houses number by eng id
    @GetMapping("/get-reports-num-by-eng-id/{id}")
    public ResponseEntity<?> getReportsNumByEngId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(engineerService.getReportsNumByEngId(id));
    }

}
