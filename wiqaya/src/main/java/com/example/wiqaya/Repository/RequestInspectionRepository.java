package com.example.wiqaya.Repository;

import com.example.wiqaya.Model.RequestInspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestInspectionRepository extends JpaRepository<RequestInspection, Integer> {

    RequestInspection findRequestInspectionById(Integer id);
}
