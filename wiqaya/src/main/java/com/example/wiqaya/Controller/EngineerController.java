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
        return ResponseEntity.status(200).body(engineerService.findAllEngineers());
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
}
