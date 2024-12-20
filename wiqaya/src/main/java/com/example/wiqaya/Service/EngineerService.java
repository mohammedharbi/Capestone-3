package com.example.wiqaya.Service;

import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.DTO.OUT.EngineerDTOOUT;
import com.example.wiqaya.Model.Engineer;
import com.example.wiqaya.Model.Report;
import com.example.wiqaya.Model.RequestInspection;
import com.example.wiqaya.Repository.EngineerRepository;
import com.example.wiqaya.Repository.ReportRepository;
import com.example.wiqaya.Repository.RequestInspectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EngineerService {

    private final EngineerRepository engineerRepository;
    private final RequestInspectionRepository requestInspectionRepository;
    private final ReportRepository reportRepository;


    public List<EngineerDTOOUT> getAllEngineers() {
        List<Engineer> engineers = engineerRepository.findAll();
        List<EngineerDTOOUT> engineerDTOOUTS = new ArrayList<>();
        for (Engineer engineer : engineers) {
            EngineerDTOOUT engineerDTOOUT = new EngineerDTOOUT(engineer.getFullName(), engineer.getPhoneNumber(), engineer.getStatus(), engineer.getAvailability());
            engineerDTOOUTS.add(engineerDTOOUT);
        }
        return engineerDTOOUTS;
    }


    public void addEngineer(Engineer engineer) {
        engineer.setStatus("UnderReview");
        engineer.setAvailability(false);
        engineerRepository.save(engineer);
    }

    public void updateEngineer(Integer id,Engineer engineer) {
        Engineer engineer1 = engineerRepository.findEngineerById(id);
        if (engineer1 != null) {
            engineer1.setFullName(engineer.getFullName());
            engineer1.setPhoneNumber(engineer.getPhoneNumber());
            engineer1.setEmail(engineer.getEmail());
            //engineer1.setAccreditationNumber(engineer.getAccreditationNumber()); is there any need to update AccreditationNumber ?? i don't think so.
            engineer1.setCity(engineer.getCity());
            engineerRepository.save(engineer1);
        }else throw new ApiException("Engineer Not Found");
    }

    public void deleteEngineer(Integer id) {
        Engineer engineer1 = engineerRepository.findEngineerById(id);
        if (engineer1 != null) {
            engineerRepository.delete(engineer1);
        }else throw new ApiException("Engineer Not Found");
    }

    // --------------------------------------------------------

    // Endpoint No.12
    //sara
    // Eng check on his status if he get approved or rejected
    public String checkMyStatus(Integer id) {
        Engineer engineer = engineerRepository.findEngineerById(id);
        if(engineer==null) {
           throw new ApiException("Engineer not found");
        }
        // Get the status and rejection reason
        String status = engineer.getStatus();
        String rejectionReason = engineer.getRejectionReason();

        // Check the status and return the appropriate response
        if ("approved".equalsIgnoreCase(status)) {
            return  status + " Congrats !" ;
        } else if ("rejected".equalsIgnoreCase(status)) {
            return "Rejected: " + rejectionReason;
        } else {
            return "Status: " + status;
        }
    }


    // Endpoint No.13
    //sara
    // Eng get all the request Inspection that assign to him
    public List<RequestInspection> getAllRequestInspectionByEngId(Integer id){
     if(!engineerRepository.existsById(id)){
         throw new ApiException("Engineer not found");
     }
      List<RequestInspection> engRequests = requestInspectionRepository.findRequestInspectionByEngineerId(id);
      return engRequests.isEmpty()? null:engRequests;
    }

    // Endpoint No.30
    //Sara
    // get eng reported houses number by eng id
    // take the eng id , check if he's existing
    // get list of reports checked by that eng using query
    // return the number of done reports by that eng ,, list size
    public Integer getReportsNumByEngId(Integer id){
        Engineer engineer = engineerRepository.findEngineerById(id);
        if(engineer==null){
            throw new ApiException("Engineer not found");
        }
        List<Report> reports = reportRepository.findAllByEngineerId(id);
        if(reports==null){
            throw new ApiException("No reports found");
        }
        return reports.size();
    }


}
