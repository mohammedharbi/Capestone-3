package com.example.wiqaya.Repository;

import com.example.wiqaya.Model.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EngineerRepository extends JpaRepository<Engineer,Integer> {

    Engineer findEngineerById(Integer id);

    @Query("SELECT e FROM Engineer e " +
            "WHERE e.availability = true " +
            "AND (SELECT COUNT(r) FROM RequestInspection r WHERE r.engineer = e AND r.date = :date) < 5")
    List<Engineer> findAvailableEngineersForDate(LocalDate date);

}
