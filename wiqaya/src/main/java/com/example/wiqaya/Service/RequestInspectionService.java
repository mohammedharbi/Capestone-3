package com.example.wiqaya.Service;

import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.DTO.IN.RequestInspectionDTOIN;
import com.example.wiqaya.DTO.OUT.RequestInspectionDTOOUT;
import com.example.wiqaya.Model.Engineer;
import com.example.wiqaya.Model.House;
import com.example.wiqaya.Model.RequestInspection;
import com.example.wiqaya.Model.User;
import com.example.wiqaya.Repository.EngineerRepository;
import com.example.wiqaya.Repository.HouseRepository;
import com.example.wiqaya.Repository.RequestInspectionRepository;
import com.example.wiqaya.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestInspectionService {

    private final RequestInspectionRepository requestInspectionRepository;
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final EngineerRepository engineerRepository;

    public List<RequestInspectionDTOOUT> getRequestInspection(Integer adminId) {
        User user = userRepository.findUserById(adminId);
        if(user == null || user.getRole().equals("user")){
            throw new ApiException("Not authorized User");
        }
        List<RequestInspection> requestInspections = requestInspectionRepository.findAll();
        List<RequestInspectionDTOOUT> requestInspectionDTOOUTS = new ArrayList<>();
        for (RequestInspection r : requestInspections){
            Integer house_id = r.getHouse().getId();
            String houseStatus = r.getHouse().getStatus();
            String houseType = r.getHouse().getType();
            String city = r.getHouse().getCity();
            String engName;
            String engPhoneNumber;
            if(r.getEngineer()==null){
                engName = "No Engineer assign" ;
                engPhoneNumber = "-";
            } else {
                engName = r.getEngineer().getFullName();
                engPhoneNumber = r.getEngineer().getPhoneNumber();
            }
            RequestInspectionDTOOUT requestInspectionDTOOUT = new RequestInspectionDTOOUT(r.getId(),r.getDate(),r.getStatus(),house_id,houseStatus,houseType,city,engName,engPhoneNumber);
            requestInspectionDTOOUTS.add(requestInspectionDTOOUT);
        }
        return requestInspectionDTOOUTS;
    }

    // Endpoint No.21
    //mohammed
    public void createRequestInspection(Integer user_id, Integer house_id, RequestInspectionDTOIN requestInspectionDTOIN) {
        House house = houseRepository.findHouseById(house_id);
        User user = userRepository.findUserById(user_id);

        List<RequestInspection> requestInspections=requestInspectionRepository.findAllRequestForOneUser(user_id);
        for(RequestInspection r:requestInspections){
            if(r.getHouse().getId().equals(house_id)){
                if(!r.getStatus().equalsIgnoreCase("Reported") && !r.getStatus().equalsIgnoreCase("cancelled"))
                    throw new ApiException("there is a proccesing request in this house");
            }
        }

        if (house == null) {
            throw new ApiException("house not found");
        }
        if (user == null) {
            throw new ApiException("user not found");
        }
        if (!house.getUser().getId().equals(user_id)) {
            throw new ApiException("user doesn't own the house");
        }
        RequestInspection requestInspection = new RequestInspection(null, requestInspectionDTOIN.getDate(), "Pending", null, house, null);
        requestInspectionRepository.save(requestInspection);
    }


    public void updateRequestInspection(Integer id, RequestInspection requestInspection) {
        RequestInspection requestInspection1 = requestInspectionRepository.findRequestInspectionById(id);

        if (requestInspection1 != null) {
            requestInspection1.setStatus(requestInspection.getStatus());
            requestInspection1.setDate(requestInspection.getDate());
            requestInspectionRepository.save(requestInspection1);
        } else throw new ApiException("requestInspection not found");
    }

    public void deleteRequestInspection(Integer id) {
        RequestInspection requestInspection = requestInspectionRepository.findRequestInspectionById(id);
        if (requestInspection != null) {
            requestInspectionRepository.delete(requestInspection);
        } else throw new ApiException("requestInspection not found");
    }


    // Endpoint No.7
    //mohammed
    // admin can check the availability on specifies day (Integer day) not working
        public List<Engineer> getAvailableEngineersForDate(LocalDate date) {
            if (date == null) {
                throw new IllegalArgumentException("Date cannot be null");
            }

            // Fetch available engineers using the custom repository query
            List<Engineer> availableEngineers = engineerRepository.findAvailableEngineersForDate(date);

            if (availableEngineers.isEmpty()) {
                throw new ApiException("No available engineers found for the selected date");
            }

            return availableEngineers;
        }




    // Endpoint No.8
    //Mohammed
    public void assignEng(Integer adminId, Integer engId, Integer requestInspectionId){
        User admin = userRepository.findUserById(adminId);
        if (admin == null) {throw new ApiException("user not found");}
        if (!admin.getRole().equalsIgnoreCase("admin")){throw new ApiException("Not Authorized user");}

        RequestInspection requestInspection = requestInspectionRepository.findRequestInspectionById(requestInspectionId);
        if (requestInspection == null) {throw new ApiException("requestInspection not found");}

        Engineer engineer = engineerRepository.findEngineerById(engId);
        if (engineer == null) {throw new ApiException("engineer not found");}

        House house = houseRepository.findHouseById(requestInspection.getHouse().getId());
        if (house == null) {throw new ApiException("house not found");}
        if (house.getStatus().equalsIgnoreCase("pending_inspection") || house.getStatus().equalsIgnoreCase("checked")){
            throw new ApiException("inspection already assigned for this house");
        }

        // Assign engineer to request inspection
        requestInspection.setEngineer(engineer);
        requestInspection.setStatus("AssignedToEnginner");



//        // Update engineer's list of inspections
//        engineer.getRequestInspections().add(requestInspection);

        requestInspectionRepository.save(requestInspection);
        engineerRepository.save(engineer);
        // Update house status
        house.setStatus("pending_inspection");
        houseRepository.save(house);

    }

    // Endpoint No.
    // User check the request inspection status
    // take the request id search if it's existing
    // if it exists return the status
    public String checkRequestStatus(Integer id){
        RequestInspection requestInspection = requestInspectionRepository.findRequestInspectionById(id);
        if(requestInspection==null){
            throw new ApiException("No request found with the given id");
        }
        return requestInspection.getStatus();
    }


    // Endpoint No.9
    //hadeel
    public void CancelRequestInspection(Integer userid, Integer requestid){
    //check request id if found
       RequestInspection requestInspection = requestInspectionRepository.findRequestInspectionById(requestid);
       if(requestInspection==null) throw new ApiException("request not found");

        //check if the user own this request
        if (!requestInspection.getHouse().getUser().getId().equals(userid)) {
            throw new ApiException("User is not authorized to cancel this request");
        }

        if(requestInspection.getStatus().equalsIgnoreCase("cancelled"))
            throw new ApiException("Request already cancelled");

        if(requestInspection.getStatus().equalsIgnoreCase("Reported"))
            throw new ApiException("Request already Reported");


        Period period = Period.between(LocalDate.now(),requestInspection.getDate());

        if (period.getDays() < 1) {
            throw new ApiException(period.getDays()+"  Request can only be cancelled 1 day before the inspection date");
        }

       requestInspection.setStatus("cancelled");
        requestInspectionRepository.save(requestInspection);

    }

}