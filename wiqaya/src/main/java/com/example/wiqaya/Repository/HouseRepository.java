package com.example.wiqaya.Repository;

import com.example.wiqaya.Model.House;
import com.example.wiqaya.Model.User;
import com.example.wiqaya.Service.HouseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House,Integer> {
    House findHouseById(Integer id);

    List<House>findHouseByUser(User user);
    House findHouseByUserAndCityAndLocationAndType(User user, String city, String location, String type);

    List<House> findHouseByConditionPercentageLessThan(Integer percentage);

    // to get form the DB houses have that type and city
    List<House> findHousesByCityAndType(String city, String type);

    List<House> findHousesByStatus( String status);
}
