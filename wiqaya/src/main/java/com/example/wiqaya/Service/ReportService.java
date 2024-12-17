package com.example.wiqaya.Service;


import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.DTO.IN.ReportDTOIN;
import com.example.wiqaya.DTO.OUT.ReportDTOOUT;
import com.example.wiqaya.Model.Engineer;
import com.example.wiqaya.Model.Report;
import com.example.wiqaya.Repository.EngineerRepository;
import com.example.wiqaya.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private  final ReportRepository reportRepository;
    private final EngineerRepository engineerRepository;

    public List<ReportDTOOUT> getAll(){
        List<Report>reports=reportRepository.findAll();
        if(reports.isEmpty())throw new ApiException("there is no report");

        List<ReportDTOOUT> dtos=new ArrayList<>();
        for(Report r:reports){
            ReportDTOOUT reportDTOOUT =new ReportDTOOUT(r.getId(),r.getEngineer().getId(),r.getStructuralElements(),r.getFireDetection(),r.getHeatingCookingSystems(),r.getEmergencyPreparedness(),r.getVentilationSmokeManagement()
            ,r.getExteriorSurroundings(),r.getPercentage(),r.getNotes(),r.getRequiredItems(),r.getReportedDate());
            dtos.add(reportDTOOUT);
        }
        return  dtos;
    }


    public void add(Integer engineerId ,ReportDTOIN reportDTOIN){

        Engineer engineer=engineerRepository.findEngineerById(engineerId);
        if(engineer==null)throw new ApiException("there is no engineer found");


        //****** check if the

        int percentage =1;
        Report report = new Report(
                null, // ID
                reportDTOIN.getStructuralElements(),
                reportDTOIN.getFireDetection(),
                reportDTOIN.getElectricalDange(),
                reportDTOIN.getHeatingCookingSystems(),
                reportDTOIN.getEmergencyPreparedness(),
                reportDTOIN.getHazardousMaterialsStorage(),
                reportDTOIN.getVentilationSmokeManagement(),
                reportDTOIN.getExteriorSurroundings(),
                percentage, // Set the percentage here
                reportDTOIN.getNotes(),
                reportDTOIN.getRequiredItems(),
                LocalDate.now() // Set the ReportedDate as the current date
               ,engineer
        );

        // Save the report to the database
        reportRepository.save(report);
}

//    // Method to assign an engineer to a report
//    public Report assignEngineerToReport(Integer reportId, Integer engineerId) {
//        // Retrieve the report by ID
//        Report report = reportRepository.findById(reportId)
//                .orElseThrow(() -> new IllegalArgumentException("Report not found with ID: " + reportId));
//
//        // Retrieve the engineer by ID
//        Engineer engineer = engineerRepository.findById(engineerId)
//                .orElseThrow(() -> new IllegalArgumentException("Engineer not found with ID: " + engineerId));
//
//        // Assign the engineer to the report
//        report.setEngineer(engineer);
//
//        // Save the updated report with the assigned engineer
//        return reportRepository.save(report);
//
//    }

}
