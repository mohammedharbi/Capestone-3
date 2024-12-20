package com.example.wiqaya.Service;

import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.DTO.IN.HouseDTOIN;
import com.example.wiqaya.DTO.IN.ServiceProviderDTOIN;
import com.example.wiqaya.DTO.IN.UserDTOIN;
import com.example.wiqaya.DTO.OUT.ReportDTOOUT;
import com.example.wiqaya.DTO.OUT.ReviewDTOOUT;
import com.example.wiqaya.DTO.OUT.ServiceProviderDTOOUT;
import com.example.wiqaya.Model.*;
import com.example.wiqaya.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceProviderService {
private  final ServiceProviderRepository serviceProviderRepository;
private final ReviewRepository reviewRepository;
private final ReportRepository reportRepository;
private final UserRepository userRepository;
private final HouseRepository houseRepository;
    private final OfferRepository offerRepository;

    public List<ServiceProviderDTOOUT> getAll() {
        List<ServiceProvider> serviceProviders = serviceProviderRepository.findAll();
        if (serviceProviders.isEmpty()) {
            throw new ApiException("There is no service provider added");
        }
        List<ServiceProviderDTOOUT> serviceProviderDTOOUTS = new ArrayList<>();
        for (ServiceProvider s : serviceProviders) {
            List<ReviewDTOOUT> reviewDTOOUTS = new ArrayList<>();

            ServiceProviderDTOOUT serviceProviderDTOOUT = new ServiceProviderDTOOUT(
                    s.getName(),
                    s.getEmail(),
                    s.getPhoneNumber(),
                    s.getCommercialRegistration(),
                    s.getDoneOrdersNum(),
                    s.getAverageRating()
            );
            serviceProviderDTOOUTS.add(serviceProviderDTOOUT);
        }

        return serviceProviderDTOOUTS;
    }

    public void add(ServiceProviderDTOIN serviceProviderDTOIN){
        ServiceProvider serviceProvider = new ServiceProvider(null,
                serviceProviderDTOIN.getName(),serviceProviderDTOIN.getEmail(), serviceProviderDTOIN.getPhoneNumber(),
                serviceProviderDTOIN.getCommercialRegistration(),"UnderReview","",0,0.0,null,null);
        serviceProviderRepository.save(serviceProvider);
    }

    public void update(Integer id, ServiceProvider serviceProvider){
        ServiceProvider old = serviceProviderRepository.findServiceProviderById(id);
        if(old==null){
            throw new ApiException("server provider not found");
        }
        old.setName(serviceProvider.getName());
        old.setEmail(serviceProvider.getEmail());
        old.setPhoneNumber(serviceProvider.getPhoneNumber());
        serviceProviderRepository.save(old);
    }

    // -----------------------------------------------


    // Endpoint No.17
    //sara
    // user can display service provider depending on rating (Double rating )
    public List<ServiceProvider> getServiceProviderByRating(Integer rating){
        List<ServiceProvider> providerWithRatingAbove = new ArrayList<>();
//        if(providerWithRatingAbove==null){
//            throw new ApiException("No service provider found");
//        }
        for (ServiceProvider s : serviceProviderRepository.findAll()){
          if(s.getAverageRating()>=rating){
            providerWithRatingAbove.add(s);
          }
        }
        return providerWithRatingAbove;
    }


    // Endpoint No. 18
    //sara
    // Service provider will be able to display all reporters if it’s publish and in the same city
    // service provider give his id in the path
    // check if the service provider exist
    // alliterate throw reports to find reports in the same city he enter
    public List<ReportDTOOUT> ProviderGetPublishedReport(Integer id, String city) {
        ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderById(id);
        if (serviceProvider == null) {
            throw new ApiException("Service Provider not found");
        }
        List<Report> reports = reportRepository.findAllByIsPublishedAndCity(city);

        List<ReportDTOOUT> reportDTOOUTS = new ArrayList<>();
        for (Report report : reports) {
            if (report.getIsPublished().equals(true) && report.getHouse().getCity().equals(city)) {
                // Convert Report to ReportDTOOUT
                ReportDTOOUT dtoOut = new ReportDTOOUT(
                        report.getId(),
                        report.getEngineer().getId(),
                        report.getStructuralElements(),
                        report.getFireDetection(),
                        report.getHeatingCookingSystems(),
                        report.getEmergencyPreparedness(),
                        report.getVentilationSmokeManagement(),
                        report.getExteriorSurroundings(),
                        report.getPercentage(),
                        report.getNotes(),
                        report.getRequiredItems(),
                        report.getReportedDate(),
                        report.getHouse().getCity()
                );
                reportDTOOUTS.add(dtoOut);
            }
        }
        return reportDTOOUTS;
    }

    // Endpoint No.19
    //mohammed
    public String checkMyStatusServiceProvider(Integer id) {
        ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderById(id);

        if(serviceProvider==null) {
            throw new ApiException("serviceProvider not found");
        }

        // Get the status and rejection reason
        String status = serviceProvider.getStatus();
        String rejectionReason = serviceProvider.getRejectionReason();

        // Check the status and return the appropriate response
        if ("approved".equalsIgnoreCase(status)) {
            return  status + " Congrats !" ;
        } else if ("rejected".equalsIgnoreCase(status)) {
            return "Rejected: " + rejectionReason;
        } else {
            return "Status: " + status;
        }
    }

    // Endpoint No.20
    //mohammed
    public List<ServiceProvider> getServiceProvidersAboveOrders(Integer userId, Integer doneOrdersNum) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new ApiException("user not found");
        }

        List<ServiceProvider> serviceProvidersAboveOrders = serviceProviderRepository.findServiceProviderByDoneOrdersNumGreaterThanEqual(doneOrdersNum);
        if (serviceProvidersAboveOrders.isEmpty()) {
            throw new ApiException("there are no serviceProviders above orders done input");
        }
        return serviceProvidersAboveOrders;
    }

    // Endpoint No.26
    //hadeel
    //service provider completeOffer(Integer reportId) check if the service provider accepted and change the house status
    public void completeOffer(Integer serProviderId,Integer offerId) {
        ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderById(serProviderId);
        if(serviceProvider==null){
            throw new ApiException("Service provider not found");
        }
        Offer offer = offerRepository.findOfferById(offerId);
        if(offer==null){
            throw new ApiException("offer not found");
        }
        // Check if the service provider is authorized to complete the offer
        if (!offer.getServiceProvider().getId().equals(serProviderId)){
          throw new ApiException("Unauthorized: You are not allowed to complete this offer.");
        }

        // Update the offer and house statuses
        offer.setStatus("Completed");
        offerRepository.save(offer);

        House house = offer.getReport().getHouse();
        house.setStatus("completed");
        houseRepository.save(house);

        serviceProvider.setDoneOrdersNum(serviceProvider.getDoneOrdersNum() + 1);
        serviceProviderRepository.save(serviceProvider);
        System.out.println("Offer completed and house status updated to 'Completed' for house ID: " + house.getId());
    }


}
