package com.example.wiqaya.Controller;

import com.example.wiqaya.ApiResponse.ApiResponse;
import com.example.wiqaya.Model.Offer;
import com.example.wiqaya.Service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wiqaya/offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/get")
    public ResponseEntity getAllOffers() {
        return ResponseEntity.status(200).body(offerService.getAllOffers());
    }

    @PostMapping("/add")
    public ResponseEntity addOffer(@RequestBody @Valid Offer offer) {
        offerService.addOffer(offer);
        return ResponseEntity.status(200).body(new ApiResponse("Offer added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOffer(@PathVariable Integer id, @RequestBody @Valid Offer offer) {
        offerService.updateOffer(id, offer);
        return ResponseEntity.status(200).body(new ApiResponse("Offer updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOffer(@PathVariable Integer id) {
        offerService.deleteOffer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Offer deleted"));
    }

}
