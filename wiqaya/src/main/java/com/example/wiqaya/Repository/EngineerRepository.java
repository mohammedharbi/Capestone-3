package com.example.wiqaya.Repository;

import com.example.wiqaya.Model.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineerRepository extends JpaRepository<Engineer,Integer> {

    Engineer findEngineerById(Integer id);
}
