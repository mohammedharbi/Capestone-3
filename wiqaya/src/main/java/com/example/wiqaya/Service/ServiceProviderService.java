package com.example.wiqaya.Service;

import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.DTO.IN.HouseDTOIN;
import com.example.wiqaya.DTO.IN.ServiceProviderDTOIN;
import com.example.wiqaya.DTO.IN.UserDTOIN;
import com.example.wiqaya.DTO.OUT.ReportDTOOUT;
import com.example.wiqaya.Model.*;
import com.example.wiqaya.Repository.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceProviderService {
private  final ServiceProviderRepository serviceProviderRepository;

    public List<ServiceProvider> getAll(){
        List<ServiceProvider>serviceProviders=serviceProviderRepository.findAll();
        if(serviceProviders.isEmpty())throw new ApiException("there is no service provider added");
       return serviceProviders;
    }


    public void add(ServiceProviderDTOIN serviceProviderDTOIN){
        ServiceProvider serviceProvider = new ServiceProvider(null,
                serviceProviderDTOIN.getName(),serviceProviderDTOIN.getEmail(), serviceProviderDTOIN.getPhoneNumber(),
                serviceProviderDTOIN.getCommercialRegistration(),"UnderReview",null,0,0.0,null,null);
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

    //---------------------endpoints

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
}
