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

    @GetMapping("/get-service-providers-above-orders/userId/{userId}/order-done-number/{orderDoneNumber}")
    public ResponseEntity<?> getServiceProvidersAboveOrders(@PathVariable Integer userId,@PathVariable Integer orderDoneNumber){
        return ResponseEntity.status(200).body(serviceProviderService.getServiceProvidersAboveOrders(userId,orderDoneNumber));
    }

    // Endpoint No.
    // user can display service provider depending on rating (Double rating )
    @GetMapping("/get-service-provider-by-rating/{rating}")
    public ResponseEntity<?> getServiceProviderByRating(@PathVariable Integer rating){
     return ResponseEntity.status(200).body(serviceProviderService.getServiceProviderByRating(rating));
    }

    @PutMapping("/complete-offer-ser-provider-id/{serProviderId}/offer-id/{offerId}")
    public ResponseEntity<?> completeOffer(@PathVariable Integer serProviderId, @PathVariable Integer offerId){
        serviceProviderService.completeOffer(serProviderId,offerId);
        return ResponseEntity.status(200).body(new ApiResponse("Service provider installing safety items"));
    }
}
