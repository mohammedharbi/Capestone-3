package com.example.wiqaya.Controller;

import com.example.wiqaya.ApiResponse.ApiResponse;
import com.example.wiqaya.DTO.IN.OfferDTOIN;
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

    //hadeel
    @GetMapping("/get-offers/userid/{userid}/rportid/{reportid}")
    public ResponseEntity getOffersByReport(@PathVariable Integer userid, @PathVariable Integer reportid){
       return ResponseEntity.status(200).body(offerService.getOffersByReport(userid,reportid));
    }


    //hadeel
    @PostMapping("/send-offer/providerid/{providerid}/reportid/{reportid}")
    public ResponseEntity sendOffer(@PathVariable Integer providerid, @PathVariable Integer reportid,@Valid @RequestBody OfferDTOIN offerDTOIN){
        offerService.sendOffer(providerid,reportid,offerDTOIN);
       return ResponseEntity.status(200).body(new ApiResponse("offer sent to report id: "+reportid));


    }
}
