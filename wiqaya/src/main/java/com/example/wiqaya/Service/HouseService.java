package com.example.wiqaya.Service;

import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.DTO.IN.HouseDTOIN;
import com.example.wiqaya.DTO.OUT.HouseDTOOUT;
import com.example.wiqaya.Model.House;
import com.example.wiqaya.Model.User;
import com.example.wiqaya.Repository.HouseRepository;
import com.example.wiqaya.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;
    private final UserRepository userRepository;

    // get all houses
    public List<HouseDTOOUT> getAllHouses(){
        List<House> houses = houseRepository.findAll();
        List<HouseDTOOUT> houseDTOOUTS = new ArrayList<>();
        for (House h : houses){
          String username = h.getUser().getUsername(); // get username via house model to reach his username
          HouseDTOOUT houseDTOOUT = new HouseDTOOUT(h.getCity(),h.getLocation(),h.getConditionPercentage(),h.getType(),h.getStatus(),username);
          houseDTOOUTS.add(houseDTOOUT);
        }
        return houseDTOOUTS;
    }

    // add new House for a user
    public void addHouse(Integer user_id,HouseDTOIN houseDTOIN){
       User user = userRepository.findUserById(user_id);  // Find the user
       if(user==null){
           throw new ApiException("User not found");
       }
        // Check if the user already has a house with the same city, location, and type
        House existingHouse = houseRepository.findHouseByUserAndCityAndLocationAndType(user, houseDTOIN.getCity(), houseDTOIN.getLocation(), houseDTOIN.getType());
        if (existingHouse != null) {
            throw new ApiException("You already have a house with the same details");
        }
       House house = new House(null,houseDTOIN.getCity(),houseDTOIN.getLocation(),0 // i put it 0 cuz it give me an error (Column 'condition_percentage' cannot be null)
               ,houseDTOIN.getType(),"un_checked",user,null,null);
      houseRepository.save(house);
    }

    // update house info
    public void updateHouse(Integer id,House house){
        House oldHouse = houseRepository.findHouseById(id);
        if(oldHouse==null){
            throw new ApiException("House not found");
        }
        oldHouse.setCity(house.getCity().trim());
        oldHouse.setType(house.getType().trim());
        oldHouse.setLocation(house.getLocation().trim());
        houseRepository.save(oldHouse);
    }

    // delete house
    public void deleteHouse(Integer userId,Integer houseId){
        User user = userRepository.findUserById(userId);
        if(user==null){
            throw new ApiException("User not found");
        }
        House house = houseRepository.findHouseById(houseId);
        if(house==null){
            throw new ApiException("House not found");
        }
        user.getHouses().remove(house);
        houseRepository.delete(house);
        userRepository.save(user);
    }


    public List<House> findHouseByConditionPercentageLessThan(Integer adminId,Integer conditionPercentage){
        User admin = userRepository.findUserById(adminId);
        if(admin==null){throw new ApiException("User not found");}
        if (!admin.getRole().equalsIgnoreCase("admin")) {
            throw new ApiException("Not Authorized: Only admin can perform this action");
        }

        List<House> houses = houseRepository.findHouseByConditionPercentageLessThan(conditionPercentage);
        if(houses==null || houses.isEmpty()){
            throw new ApiException("Houses not found with below the condition percentage");
        }
        return houses;
    }

 }
