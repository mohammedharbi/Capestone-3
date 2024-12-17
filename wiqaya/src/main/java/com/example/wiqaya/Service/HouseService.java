package com.example.wiqaya.Service;

import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.Model.House;
import com.example.wiqaya.Model.User;
import com.example.wiqaya.Repository.HouseRepository;
import com.example.wiqaya.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;
    private final UserRepository userRepository;

    // get all houses
    public List<House> getAllHouses(){
        return houseRepository.findAll();
    }

    // add new House for a user
    public void addHouse(Integer user_id,House house){
       User user = userRepository.findUserById(user_id);
       if(user==null){
           throw new ApiException("User not found");
       }
       house.setUser(user);
      houseRepository.save(house);
    }


    // update house info
    public void updateHouse(Integer id,House house){
        House oldHouse = houseRepository.findHouseById(id);
        if(oldHouse==null){
            throw new ApiException("House not found");
        }
        oldHouse.setCity(house.getCity());
        oldHouse.setType(house.getType());
        oldHouse.setLocation(house.getLocation());
        houseRepository.save(oldHouse);
    }

    // delete house
    public void deleteHouse(Integer id){
        House house = houseRepository.findHouseById(id);
        if(house==null){
            throw new ApiException("House not found");
        }
        houseRepository.delete(house);
    }

 }
