package com.example.wiqaya.Service;

import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.DTO.OUT.OfferDTOOUT;
import com.example.wiqaya.DTO.OUT.ReportDTOOUT;
import com.example.wiqaya.Model.House;
import com.example.wiqaya.Model.Offer;
import com.example.wiqaya.Model.Report;
import com.example.wiqaya.Repository.HouseRepository;
import com.example.wiqaya.Repository.OfferRepository;
import com.example.wiqaya.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final HouseRepository houseRepository;
    private final ReportRepository reportRepository;

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


    //Hadeel
    public List<OfferDTOOUT> getOffersByReport(Integer userid, Integer reportid){
        Report report =reportRepository.findReportById(reportid);
        if(report==null)throw new ApiException("no report found with this id");

        House house=houseRepository.findHouseById(report.getHouse().getId());
        if(!(house.getUser().getId().equals(userid))){
            throw  new ApiException("user not authorize to this report");
        }

       List<Offer>offers=offerRepository.findOffersByReport(report);
        if(offers.isEmpty() || offers==null)throw  new ApiException("there is no offers yet");

        List<OfferDTOOUT> dtos=new ArrayList<>();
        for(Offer o:offers){
            OfferDTOOUT offerDTOOUT =new OfferDTOOUT(o.getId(),o.getDescription(),o.getStatus(),o.getPrice(),o.getServiceProvider());
            dtos.add(offerDTOOUT);
        }
        return  dtos;

    }

}
