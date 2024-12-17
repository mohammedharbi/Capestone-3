package com.example.wiqaya.Repository;

import com.example.wiqaya.Model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House,Integer> {
    House findHouseById(Integer id);

}
