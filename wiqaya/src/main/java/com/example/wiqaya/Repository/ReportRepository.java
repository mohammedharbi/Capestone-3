package com.example.wiqaya.Repository;

import com.example.wiqaya.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report,Integer> {

    Report findReportById(Integer id);
}
