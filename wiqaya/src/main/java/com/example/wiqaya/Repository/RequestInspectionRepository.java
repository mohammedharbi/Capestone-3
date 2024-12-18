package com.example.wiqaya.Repository;

import com.example.wiqaya.Model.Engineer;
import com.example.wiqaya.Model.RequestInspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RequestInspectionRepository extends JpaRepository<RequestInspection, Integer> {

    RequestInspection findRequestInspectionById(Integer id);
    Integer countByEngineerAndDate(Engineer engineer, LocalDate date);

    @Query("SELECT e FROM Engineer e " +
            "WHERE e.availability = true " +
            "AND e.status = 'Approved' " +
            "AND (SELECT COUNT(r) FROM RequestInspection r WHERE r.engineer = e AND r.date = :date) < 5")
    List<Engineer> findAvailableEngineersForDate(@Param("date") LocalDate date);

    @Query("SELECT r FROM RequestInspection r WHERE r.date = :date")
    List<RequestInspection> findByDate(@Param("date") LocalDate date);

    @Query("select r from RequestInspection r where r.engineer.id=?1")
    List<RequestInspection> findRequestInspectionByEngineerId(Integer id);

    // get All request Inspection for one user by user id
    @Query("select r from RequestInspection  r where r.house.user.id=?1")
    List<RequestInspection> findAllRequestForOneUser(Integer id);
}
