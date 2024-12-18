package com.example.wiqaya.Repository;

import com.example.wiqaya.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report,Integer> {

    Report findReportById(Integer id);

    @Query("select r from Report r where r.isPublished=true and r.house.city=?2")
    List<Report> findAllByIsPublishedAndCity(String city);
}
