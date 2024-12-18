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

    public void addRequestInspection(Integer user_id, Integer house_id, RequestInspectionDTOIN requestInspectionDTOIN) {
        House house = houseRepository.findHouseById(house_id);
        User user = userRepository.findUserById(user_id);
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

    // is there any need to update a request ??
//    public void updateRequestInspection(Integer id, RequestInspection requestInspection) {
//        RequestInspection requestInspection1 = requestInspectionRepository.findRequestInspectionById(id);
//
//        if (requestInspection1 != null) {
//            requestInspection.setStatus(requestInspection.getStatus());
//            requestInspectionRepository.save(requestInspection);
//        }else throw new ApiException("requestInspection not found");
//    }

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


//    public List<Engineer> isEngineerAvailable(LocalDate date) {
//        List<RequestInspection> requestInspections = requestInspectionRepository.findAll();
//        List<Engineer> engineers = engineerRepository.findAll();
//        if (engineers == null|| engineers.isEmpty()) {
//            throw new ApiException("engineers not found");
//        }

        //flow 1
//        List<Engineer> availableEngineers = new ArrayList<>();
//        for (Engineer engineer : engineers) {
//            Integer requestsForDate = requestInspectionRepository.countByEngineerAndDate(engineer, date);
//            if (requestsForDate < 5) {
//                availableEngineers.add(engineer);
//            }
//        }
        //flow 2

        // Endpoint No.2
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


        //flow 3
//        List<Engineer> availableEngineers = new ArrayList<>();
//        for (Engineer engineer : engineers) {
//            Integer countEngineer = 0;
//            for (RequestInspection requestInspection : requestInspections) {
//                if (requestInspection.getDate().equals(date)) {
//                    if (requestInspection.getEngineer().getId().equals(engineer.getId())) {
//                         countEngineer ++;
//                    }
//                }
//            }
//            if (countEngineer < 5){
//                availableEngineers.add(engineer);
//            }
//        }
//        return availableEngineers;
//    }


    // Endpoint No.3
    // assign eng to a requestInspection
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

        // Update house status
        house.setStatus("pending_inspection");

//        // Update engineer's list of inspections
//        engineer.getRequestInspections().add(requestInspection);

        requestInspectionRepository.save(requestInspection);
        engineerRepository.save(engineer);
        houseRepository.save(house);


    }


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

//احسب الفرق بين تاريخ اليوم وتاريخ الطلب

        Period period = Period.between(LocalDate.now(),requestInspection.getDate());

// اذا كان اقل من يوم ثرو اكسبشن
        if (period.getDays() < 1) {
            throw new ApiException(period.getDays()+"  Request can only be cancelled 1 day before the inspection date");
        }

       requestInspection.setStatus("cancelled");
        requestInspectionRepository.save(requestInspection);

    }
}