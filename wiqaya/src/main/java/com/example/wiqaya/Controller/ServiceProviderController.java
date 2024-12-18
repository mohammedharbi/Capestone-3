package com.example.wiqaya.Controller;


import com.example.wiqaya.ApiResponse.ApiResponse;
import com.example.wiqaya.DTO.IN.HouseDTOIN;
import com.example.wiqaya.DTO.IN.ServiceProviderDTOIN;
import com.example.wiqaya.Model.ServiceProvider;
import com.example.wiqaya.Service.ServiceProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wiqaya/service-provider")
@RequiredArgsConstructor
public class ServiceProviderController {
    private final ServiceProviderService serviceProviderService;

    @GetMapping("/get-all")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(serviceProviderService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid ServiceProviderDTOIN serviceProviderDTOIN ){
       serviceProviderService.add(serviceProviderDTOIN);
        return ResponseEntity.status(200).body(new ApiResponse("Service provider added"));
    }

    @PutMapping("/update")
    public ResponseEntity update(@PathVariable Integer id,ServiceProvider serviceProvider){
        serviceProviderService.update(id,serviceProvider);
        return  ResponseEntity.status(200).body(new ApiResponse("service provider updated"));
    }

    @GetMapping("/check-my-status-service-provider/service-provider/{providerId}")
    public ResponseEntity<?> checkMyStatus(@PathVariable Integer providerId){
        String response = serviceProviderService.checkMyStatusServiceProvider(providerId);
        return ResponseEntity.status(200).body(response);
    }

    // Endpoint No.
    //Service provider will be able to display all reporters if it’s publish and in the same city
    @GetMapping("/provider-display-publish-reports/provider-id/{id}/city/{city}")
    public ResponseEntity<?> ProviderGetPublishedReport(@PathVariable Integer id,@PathVariable String city){
        return ResponseEntity.status(200).body(serviceProviderService.ProviderGetPublishedReport(id,city));
    }

}
