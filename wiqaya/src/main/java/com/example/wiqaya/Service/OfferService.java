package com.example.wiqaya.Service;

import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.Model.Offer;
import com.example.wiqaya.Repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }
    public void addOffer(Offer offer) {
        offerRepository.save(offer);
    }
    public void updateOffer(Integer id,Offer offer) {
        Offer oldOffer = offerRepository.findOfferById(id);

        if (oldOffer != null) {
            oldOffer.setDescription(offer.getDescription());
            oldOffer.setPrice(offer.getPrice());
            oldOffer.setStatus(offer.getStatus());
            offerRepository.save(oldOffer);
        }else throw new ApiException("Offer Not Found");
    }
    public void deleteOffer(Integer id) {
        Offer oldOffer = offerRepository.findOfferById(id);
        if (oldOffer != null) {
            offerRepository.delete(oldOffer);
        }else throw new ApiException("Offer Not Found");
    }

}
