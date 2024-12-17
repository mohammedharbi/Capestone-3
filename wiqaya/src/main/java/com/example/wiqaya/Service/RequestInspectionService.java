package com.example.wiqaya.Service;

import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.DTO.IN.RequestInspectionDTOIN;
import com.example.wiqaya.DTO.OUT.RequestInspectionDTOOUT;
import com.example.wiqaya.Model.House;
import com.example.wiqaya.Model.RequestInspection;
import com.example.wiqaya.Model.User;
import com.example.wiqaya.Repository.HouseRepository;
import com.example.wiqaya.Repository.RequestInspectionRepository;
import com.example.wiqaya.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestInspectionService {

    private final RequestInspectionRepository requestInspectionRepository;
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;

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

    public void addRequestInspection(Integer user_id,Integer house_id,RequestInspectionDTOIN requestInspectionDTOIN) {
        House house = houseRepository.findHouseById(house_id);
        User user = userRepository.findUserById(user_id);

        if(house==null){throw new ApiException("house not found");}
        if(user==null){throw new ApiException("user not found");}
        if (!house.getUser().getId().equals(user_id)) {throw new ApiException("user doesn't own the house");}
        RequestInspection requestInspection = new RequestInspection(null,requestInspectionDTOIN.getDate(),"Pending",null,house,null);
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
        }else throw new ApiException("requestInspection not found");
    }

    public void deleteRequestInspection(Integer id) {
        RequestInspection requestInspection = requestInspectionRepository.findRequestInspectionById(id);
        if (requestInspection != null) {
            requestInspectionRepository.delete(requestInspection);
        }else throw new ApiException("requestInspection not found");
    }

}
